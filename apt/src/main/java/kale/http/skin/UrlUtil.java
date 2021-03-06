package kale.http.skin;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @date 2015/8/5
 * @see "http://zhidao.baidu.com/link?url=0xxHVk_xgW8xRoRQ0VOwGuTQtxGq8GTs2WAsVq72GfXIntT6ib4EUa4mUMx93b1yg0gagUrlXsitVjqBOEOjLqgOt7D6_mAyVtMiHyzF8Xi"
 */
class UrlUtil {

    /**
     * /user/test/ - > user.test
     */
    static String url2packageName(String url) {
        url = getRealUrl(url);
        String packageName = url.replaceAll("/", ".");
        if (packageName.startsWith(".")) {
            packageName = packageName.substring(1);
        }
        if (packageName.substring(packageName.length() - 1).equals(".")) {
            packageName = packageName.substring(0, packageName.length() - 1);
        }
        return packageName;
    }

    static String getRealUrl(String url) {
        if (url.contains("?")) {
            return url.substring(0, url.indexOf("?"));
        } else {
            return url;
        }
    }

    /**
     *
     * @param strURL url
     * @return url
     */
    private static String TruncateUrlPage(String strURL) {
        String strAllParam = null;
        String[] arrSplit;

        strURL = strURL.trim();

        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1) {
            if (arrSplit.length > 1) {
                if (arrSplit[1] != null) {
                    strAllParam = arrSplit[1];
                }
            }
        }
        return strAllParam;
    }

    /**
     *
     * example: "index.jsp?Action=del&id=123"，parse - > Action:del,id:123
     *
     * @param url url
     * @return url
     */
    static Map<String, String> getParams(String url) {
        Map<String, String> mapRequest = new LinkedHashMap<>();

        String[] arrSplit;

        String strUrlParam = TruncateUrlPage(url);
        if (strUrlParam == null) {
            return mapRequest;
        }
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual;
            arrSplitEqual = strSplit.split("[=]");

            if (arrSplitEqual.length > 1) {
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (!arrSplitEqual[0].isEmpty()) {
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

}
