/*
 * copywrite 2015-2020 智慧享联
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 */

package com.example.commenttool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by baiyuliang on 15/12/2.
 */
public class XListView extends ListView implements OnScrollListener {
	private static final int REFRESH_DONE = 0;// 下拉刷新完成
	private static final int PULL_TO_REFRESH = 1;// 下拉中（下拉高度未超出headview高度）
	private static final int RELEASE_TO_REFRESH = 2;// 准备刷新（下拉高度超出headview高度）
	private static final float REFRESH_RATIO = 2.5f;// 下拉系数,越大下拉灵敏度越低
	private static final int REFRESHING = 3;// 刷新中
	private static final float LOAD_RATIO = 3;// 上拉系数
	private static final int LOAD_DONE = 4;// 上拉加载完成
	private static final int PULL_TO_LOAD = 5;// 上拉中（上拉高度未超出footerview高度）
	private static final int RELEASE_TO_LOAD = 6;// 上拉中（上拉高度超出footerview高度）
	private static final int LOADING = 7;// 加载中
	private RelativeLayout headerView;// headerView布局
	private RefreshAnimView refreshAnimView;
	private RefreshImageView headerViewProgressBar;
	private ProgressBar footerViewProgressBar;
	private TextView footerViewTextView;
	private LinearLayout footerView;// footerView布局

	private int headerViewHeight;// headerView高度
	private int refreshstate;// 下拉刷新状态
	private boolean isScrollFirst;// 是否滑动到顶部
	private boolean isRefreshable;// 是否启用下拉刷新
	private int footerViewHeight;// footerView高度
	private int loadstate;// 上拉加载状态
	private boolean isScrollLast;// 是否滑动到底部
	private int totalcount;// item总数量
	private boolean isLoadable;// 是否启用上拉加载

	private float startY, // 手指落点
			offsetY;// 手指滑动的距离

	// 监听接口
	private IXListViewListener onListViewListener;
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0) {
				refreshstate = REFRESH_DONE;
				changeHeaderByState(refreshstate);
			}
		}

	};

	public XListView(Context context) {
		super(context);
		init(context);
	}

	public XListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public XListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	/** 初始化 */
	private void init(Context context) {
		setOverScrollMode(View.OVER_SCROLL_NEVER);
		setOnScrollListener(this);

		headerView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_head, null, false);
		refreshAnimView = (RefreshAnimView) headerView.findViewById(R.id.refreshAnimView);
		headerViewProgressBar = (RefreshImageView) headerView.findViewById(R.id.progressbar);

		footerView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.xlistview_footer, null, false);
		footerViewTextView = (TextView) footerView.findViewById(R.id.xlistview_footer_hint_textview);
		footerViewProgressBar = (ProgressBar) footerView.findViewById(R.id.xlistview_footer_progressbar);

		addHeaderView(headerView);
		addFooterView(footerView);

		headerView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				headerViewHeight = headerView.getMeasuredHeight();
				headerView.setPadding(0, -headerViewHeight, 0, 0);
				getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
		footerViewHeight = context.getResources().getDimensionPixelSize(R.dimen.refresh_footer_height);
		footerView.setPadding(0, 0, 0, -footerViewHeight);
		// 初始化刷新状态
		refreshstate = REFRESH_DONE;
		// 初始化加载状态
		loadstate = LOAD_DONE;

		// 默认启用
		isRefreshable = true;
		isLoadable = true;
	}

	public void showLeftTips() {
		RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) footerViewProgressBar.getLayoutParams();
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) footerViewTextView.getLayoutParams();
		lp2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
	}

	@Override
	public void onScrollStateChanged(AbsListView absListView, int i) {
		if (i == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL && iXListViewScollorListener != null) {
			iXListViewScollorListener.onListScollor();
		}
	}

	@Override
	public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		totalcount = totalItemCount;
		if (firstVisibleItem == 0) {
			isScrollFirst = true;// 滑动到顶部
		} else {
			isScrollFirst = false;
		}
		if (firstVisibleItem + visibleItemCount == totalItemCount) {
			isScrollLast = true;// 滑动到底部
		} else {
			isScrollLast = false;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (iXListViewScollorListener != null) {
				iXListViewScollorListener.onListScollor();
			}
			startY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			offsetY = ev.getY() - startY;
			/** 下拉刷新 */
			if (isRefreshable && offsetY > 0 && loadstate == LOAD_DONE && isScrollFirst && refreshstate != REFRESHING) {
				float headerViewShowHeight = offsetY / REFRESH_RATIO;
				float scaleProgress = headerViewShowHeight / headerViewHeight;// 图片放大缩小比例
				if (scaleProgress > 1) {
					scaleProgress = 1;
				}
				switch (refreshstate) {
				case REFRESH_DONE:
					refreshstate = PULL_TO_REFRESH;
					break;
				case PULL_TO_REFRESH:
					setSelection(0);
					if (headerViewShowHeight - headerViewHeight >= 0) {
						refreshstate = RELEASE_TO_REFRESH;
						changeHeaderByState(refreshstate);
					}
					break;
				case RELEASE_TO_REFRESH:
					setSelection(0);
					if (headerViewShowHeight - headerViewHeight < 0) {
						refreshstate = PULL_TO_REFRESH;
						changeHeaderByState(refreshstate);
					}
					break;
				}

				if (refreshstate == PULL_TO_REFRESH || refreshstate == RELEASE_TO_REFRESH) {
					headerView.setPadding(0, (int) (headerViewShowHeight - headerViewHeight), 0, 0);
					refreshAnimView.setCurrentProgress(scaleProgress);// 设置refreshAnimView的缩放进度
					refreshAnimView.invalidate();// 重绘
				}
			}
			/** 上拉加载更多 */
			if (isLoadable && offsetY < 0 && refreshstate == REFRESH_DONE && isScrollLast && loadstate != LOADING) {
				float footerViewShowHeight = -offsetY / LOAD_RATIO;
				switch (loadstate) {
				case LOAD_DONE:
					loadstate = PULL_TO_LOAD;
					break;
				case PULL_TO_LOAD:
					setSelection(totalcount);
					if (footerViewShowHeight - footerViewHeight >= 0) {
						loadstate = RELEASE_TO_LOAD;
						changeFooterByState(loadstate);
					}
					break;
				case RELEASE_TO_LOAD:
					setSelection(totalcount);
					if (footerViewShowHeight - footerViewHeight < 0) {
						loadstate = PULL_TO_LOAD;
						changeFooterByState(loadstate);
					}
					break;
				}

				if (loadstate == PULL_TO_LOAD || loadstate == RELEASE_TO_LOAD) {
					footerView.setPadding(0, 0, 0, (int) (footerViewShowHeight - footerViewHeight));
				}

			}
			break;
		case MotionEvent.ACTION_UP:
			/** 下拉刷新 */
			if (isRefreshable) {
				if (refreshstate == PULL_TO_REFRESH) {
					refreshstate = REFRESH_DONE;
					changeHeaderByState(refreshstate);
				}
				if (refreshstate == RELEASE_TO_REFRESH) {
					refreshstate = REFRESHING;
					changeHeaderByState(refreshstate);
					onListViewListener.onRefresh();
				}
			}

			/** 上拉加载 */
			if (isLoadable) {
				if (loadstate == PULL_TO_LOAD) {
					loadstate = LOAD_DONE;
					changeFooterByState(loadstate);
				}
				if (loadstate == RELEASE_TO_LOAD) {
					loadstate = LOADING;
					changeFooterByState(loadstate);
					onListViewListener.onLoadMore();
				}
			}
			break;
		}
		return super.onTouchEvent(ev);
	}

	/** 改变headview状态 */
	private void changeHeaderByState(int state) {
		switch (state) {
		case REFRESH_DONE:
			headerView.setPadding(0, -headerViewHeight, 0, 0);

			refreshAnimView.setVisibility(View.VISIBLE);
			headerViewProgressBar.setVisibility(View.GONE);
			break;
		case RELEASE_TO_REFRESH:
			break;
		case PULL_TO_REFRESH:
			break;
		case REFRESHING:
			headerView.setPadding(0, 0, 0, 0);
			refreshAnimView.setVisibility(View.GONE);
			headerViewProgressBar.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	/** 改变footerview状态 */
	private void changeFooterByState(int loadstate) {
		switch (loadstate) {
		case LOAD_DONE:
			footerView.setPadding(0, 0, 0, -footerViewHeight);
			footerViewTextView.setText("上拉加载更多");
			break;
		case RELEASE_TO_LOAD:
			footerViewTextView.setText("松开加载更多");
			break;
		case PULL_TO_LOAD:
			footerViewTextView.setText("上拉加载更多");
			break;
		case LOADING:
			footerViewTextView.setText("正在加载...");
			footerView.setPadding(0, 0, 0, 0);
			break;
		default:
			break;
		}
	}

	/** 下拉刷新完成 */
	public void stopRefresh() {
		if (refreshstate == REFRESHING) {
			handler.sendEmptyMessageDelayed(0, 800);
		}
	}

	/** 加载更多完成 */
	public void stopLoadMore() {
		if (loadstate == LOADING) {
			loadstate = LOAD_DONE;
			changeFooterByState(loadstate);
		}
	}

	/** 下拉刷新监听 加载更多监听 */
	public interface IXListViewListener {
		void onLoadMore();

		void onRefresh();
	}

	private IXListViewScollorListener iXListViewScollorListener;

	public void setiXListViewScollorListener(IXListViewScollorListener iXListViewScollorListener) {
		this.iXListViewScollorListener = iXListViewScollorListener;
	}

	public interface IXListViewScollorListener {
		void onListScollor();
	}

	/** 设置下拉刷新 */
	public void setXListViewListener(IXListViewListener onRefreshListener) {
		this.onListViewListener = onRefreshListener;
	}

	/** 设置是否启用下拉刷新 */
	public void setPullRefreshEnable(boolean isRefreshable) {
		this.isRefreshable = isRefreshable;
	}

	/** 设置是否启用加载更多 */
	public void setPullLoadEnable(boolean isLoadable) {
		this.isLoadable = isLoadable;
	}

}
