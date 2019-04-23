package com.mingmen.mayi.mayibanjia.bean;

/**
 * Created by Administrator on 2019/4/22.
 */

public class UpdateBean {

    /**
     * object : {"versionName":"1.0.1","downloadUrl":"213123123","modifyContent":"1测试 2测试","apkSize":null,"versionCode":"2","apkMd5":"1e6c8112864a941584b9671532ca6e4c","updateStatus":"0","code":"0","msg":"31231"}
     * status : 0000
     * msg : 成功
     */

    private ObjectBean object;
    private String status;
    private String msg;

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ObjectBean {
        /**
         * versionName : 1.0.1
         * downloadUrl : 213123123
         * modifyContent : 1测试 2测试
         * apkSize : null
         * versionCode : 2
         * apkMd5 : 1e6c8112864a941584b9671532ca6e4c
         * updateStatus : 0
         * code : 0
         * msg : 31231
         */

        private String versionName;
        private String downloadUrl;
        private String modifyContent;
        private Long apkSize;
        private Integer versionCode;
        private String apkMd5;
        private String updateStatus;
        private String code;
        private String msg;

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getModifyContent() {
            return modifyContent;
        }

        public void setModifyContent(String modifyContent) {
            this.modifyContent = modifyContent;
        }

        public Long getApkSize() {
            return apkSize;
        }

        public void setApkSize(Long apkSize) {
            this.apkSize = apkSize;
        }

        public Integer getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(Integer versionCode) {
            this.versionCode = versionCode;
        }

        public String getApkMd5() {
            return apkMd5;
        }

        public void setApkMd5(String apkMd5) {
            this.apkMd5 = apkMd5;
        }

        public String getUpdateStatus() {
            return updateStatus;
        }

        public void setUpdateStatus(String updateStatus) {
            this.updateStatus = updateStatus;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
