/*
 * copywrite 2015-2020 智慧享联
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 */

package com.example.commenttool.Uitl;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    // 每次更新版本时要修改
    public static String DES_KEY = "";
    public static String USER_ID = "";
    public static String ROUTE = "";
    public static String SESSION_ID = "";
    public static String DEVICE_INFO = "";
    public static String VERSION = "";

    public static String doGet(String url) {
        String result = null;
        HttpURLConnection connection = null;
        try {
            URL urls = new URL(url);
            connection = (HttpURLConnection) urls.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer reposn = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                reposn.append(line);
            }
            result = new String(result.getBytes("utf-8"), "UTF-8");
        } catch (Exception e) {
        }
        return result;
    }

//    public static byte[] readBytes(InputStream in) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        try {
//            byte[] bytes = new byte[1024];
//            DataInputStream r = new DataInputStream(in);
//            int l = 0;
//            while ((l = r.read(bytes)) != -1) {
//                byteArrayOutputStream.write(bytes, 0, l);
//            }
//
//            in.close();
//
//        }
//        catch (Exception e) {
//
//        }
//        return byteArrayOutputStream.toByteArray();
//    }

    //    public static String doPostAndEncypt(String url, String data) {
//        if(DES_KEY==null||DES_KEY.equals("")){
//            return "{\"code\":" + HttpConstants.NET_ERROR_KEY_ERROR + ",\"desc\":\"运行错误\"}";
//        }
//        ModelLogUtil.i("HttpPost["+ServerConfig.USER_AGENT+"]:" + url);
//        String result = null;
//        HttpPost httpRequest = new HttpPost(url);
//        try {
//            if (data != null) {
//                ModelLogUtil.i("HttpPost_data:" + data);
//                data = Des3_g.encode(data, DES_KEY);//URLEncoder.encode(Des3_g.encode(data, DES_KEY), "UTF-8");
////                ModelLogUtil.i("DES:" + DES_KEY);
//                StringEntity httpEntity = new StringEntity(data, HTTP.UTF_8);
//                httpEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING, "UTF-8"));
//                httpEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
//                httpRequest.setEntity(httpEntity);
//                httpRequest.setHeader("Accept-Encoding", "gzip, deflate");
////              httpRequest.setHeader("User-Agent", "G_E_M_D_A_L_E");
//                httpRequest.setHeader("User-Agent", ServerConfig.USER_AGENT);
//            }
//
//            HttpClient httpClient = new DefaultHttpClient();
//            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 40000);
//            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 40000);
//            // if (url.startsWith(HttpConfigs.HTTPS_SCHEME_NAME)) {
//            // Scheme scheme = HttpTask.initScheme();
//            // httpClient.getConnectionManager().getSchemeRegistry()
//            // .register(scheme);
//            // }
//            HttpResponse httpResponse = httpClient.execute(httpRequest);
//            int reponseCode = httpResponse.getStatusLine().getStatusCode();
//            if (reponseCode == HttpStatus.SC_OK) {
//                if (httpResponse.getFirstHeader("Content-Encoding") != null
//                        && httpResponse.getFirstHeader("Content-Encoding").getValue().toLowerCase().indexOf("gzip") > -1) {
//                    ModelLogUtil.e("gzip返回！");
//                    String tmpResult = dealGzipResponse(httpResponse);
//                    try {
//                        result = Des3_g.decode(tmpResult, DES_KEY);
//                    }
//                    catch (Exception e) {
//                        result = tmpResult;
//                    }
////                    ModelLogUtil.i("httpResponse：" + tmpResult);
//
//                }
//                else {
//                    ModelLogUtil.e("无压缩返回！");
//                    result = Des3_g.decode(EntityUtils.toString(httpResponse.getEntity()), DES_KEY);
//                }
//            }
//            else {
//                result = doResponseCode(reponseCode);
//            }
//        }
//        catch (Exception e) {
//            result = doException(e);
//            ModelLogUtil.e("doPost_Exception:" + e.toString());
//        }
//        ModelLogUtil.i("httpResponse：" + result);
//        return result;
//    }
//
    public static String doPostAndEncypt(String url) {
        String result = null;
        HttpURLConnection connection = null;
        try {
            URL urls = new URL(url);
            connection = (HttpURLConnection) urls.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(true);
            connection.connect();
            int a = connection.getResponseCode();
            if(a==200) {
                InputStream is = connection.getInputStream();
                BufferedReader reader = null;
                reader = new BufferedReader(new InputStreamReader(connection
                        .getInputStream(), "UTF-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result = line;
                }
            }
        }catch (Exception e){

        }finally {
            if(connection!=null){
                connection.disconnect();
            }
        }
           return result;
    }

//    public static String postImg(String url, String filePath) {
//        ModelLogUtil.i("HttpPost Img:" + url);
//        String result = null;
//        try {
//            MultipartEntity mpEntity = new MultipartEntity();
//            // MultipartEntityBuilder mpEntity =
//            // MultipartEntityBuilder.create();
//
//            ModelLogUtil.i("HttpPost filePath:" + filePath);
//            // 图片
//            if (!filePath.equals("")) {
//                FileBody file = new FileBody(new File(filePath));
//                mpEntity.addPart("image", file);
//            }
//            // 使用HttpPost对象设置发送的URL路径
//            HttpPost post = new HttpPost(url);
//            // 发送请求体
//            post.setEntity(mpEntity);
//            // 创建一个浏览器对象，以把POST对象向服务器发送，并返回响应消息
//            DefaultHttpClient client = new DefaultHttpClient();
//            HttpResponse response = client.execute(post);
//            int reponseCode = response.getStatusLine().getStatusCode();
//            if (reponseCode == HttpStatus.SC_OK) {
//                // 封装了服务器端返回的数据
//                HttpEntity responseEntity = response.getEntity();
//                // 将服务器返回的输入流 解析为 字符串
//                result = EntityUtils.toString(responseEntity);
//
//            }
//            else {
//                result = doResponseCode(reponseCode);
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            result = doException(e);
//        }
//        ModelLogUtil.i("HttpResult:" + result);
//        return result;
//    }

//    protected static String doException(Exception e) {
//        e.printStackTrace();
//        int errorCode;
//        System.out.println("出现异常啦!");
//        String msg;
//        System.out.println("异常详情：" + e.getMessage());
//        if (e instanceof IOException) {
//            System.out.println("IO异常!");
//            msg = "当前网络不可用，请检查网络";//原来语句为msg ="IO异常"
//            errorCode = HttpConstants.NET_ERROR_IO;
//        }
//        else if (e instanceof RuntimeException) {
//            // 运行时异常
//            System.out.println("运行异常!");
//            msg = "运行异常";
//            errorCode = HttpConstants.NET_ERROR_RUN;
//        }
//        else if (e instanceof java.net.UnknownHostException) {
//            // 无网络连接
//            System.out.println("网络异常!");
//            msg = "网络异常";
//            errorCode = HttpConstants.NET_ERROR_NONET;
//        }
//        else if (e instanceof java.net.SocketTimeoutException) {
//            // 连接超时
//            System.out.println("连接超时!");
//            msg = "连接超时";
//            errorCode = HttpConstants.NET_ERROR_TIMEOUT;
//        }else if (e instanceof java.security.InvalidKeyException) {
//            // 连接超时
//            System.out.println("解密异常");
//            msg = "登录超时,请重新登录";
//            errorCode = HttpConstants.NET_ERROR_KEY_ERROR;
//        }
//        else {
//            // 未知异常
//            System.out.println("未知异常!");
//            msg = "未知异常";
//            errorCode = HttpConstants.NET_ERROR_UNKNOW;
//        }
//        return "{\"code\":" + errorCode + ",\"desc\":\"" + msg + "\"}";
//    }

    /**
     * 除了200，其他都是不正常的返回
     *
     * @param responseCode
     */
    protected static String doResponseCode(int responseCode) {
        return "{\"code\":" + responseCode + ",\"desc\":\"网络异常[" + responseCode + "]\"}";
    }


}
