package com.jiang.tvlauncher.entity;

/**
 * @author: jiangyao
 * @date: 2018/5/15
 * @Email: www.fangmu@qq.com
 * @Phone: 186 6120 1018
 * TODO: vip实体类
 */
public class VIP_Entity extends BaseEntity {


    /**
     * errorcode : 1000
     * result : {"accessToken":"I1q/pN9yWdSddWo11zIsaV+4KPXTMijb1wxVBPrdhTwrZ+iPYmKcyCFXB2crPYlGlN3JrCXwIfLof0mGckJozB7UNrlzRKX5","downloadUrl":"http://oyn17egk9.bkt.clouddn.com/tv_video_3.3.0.2073_android_13097.apk","downloadUrlBak":"http://owod107fe.bkt.clouddn.com/tv_video_3.0.0.1050_android_15000.apk","packageName":"com.ktcp.tvvideo","packageNameBak":"com.ktcp.video","vtoken":"3955D9BEB4AA836B8EA6A46EDFE57ED5","vuid":"278727885"}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * accessToken : I1q/pN9yWdSddWo11zIsaV+4KPXTMijb1wxVBPrdhTwrZ+iPYmKcyCFXB2crPYlGlN3JrCXwIfLof0mGckJozB7UNrlzRKX5
         * downloadUrl : http://oyn17egk9.bkt.clouddn.com/tv_video_3.3.0.2073_android_13097.apk
         * downloadUrlBak : http://owod107fe.bkt.clouddn.com/tv_video_3.0.0.1050_android_15000.apk
         * packageName : com.ktcp.tvvideo
         * packageNameBak : com.ktcp.video
         * vtoken : 3955D9BEB4AA836B8EA6A46EDFE57ED5
         * vuid : 278727885
         */

        private String accessToken;
        private String downloadUrl;
        private String downloadUrlBak;
        private String packageName;
        private String packageNameBak;
        private String vtoken;
        private String vuid;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getDownloadUrlBak() {
            return downloadUrlBak;
        }

        public void setDownloadUrlBak(String downloadUrlBak) {
            this.downloadUrlBak = downloadUrlBak;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getPackageNameBak() {
            return packageNameBak;
        }

        public void setPackageNameBak(String packageNameBak) {
            this.packageNameBak = packageNameBak;
        }

        public String getVtoken() {
            return vtoken;
        }

        public void setVtoken(String vtoken) {
            this.vtoken = vtoken;
        }

        public String getVuid() {
            return vuid;
        }

        public void setVuid(String vuid) {
            this.vuid = vuid;
        }
    }
}
