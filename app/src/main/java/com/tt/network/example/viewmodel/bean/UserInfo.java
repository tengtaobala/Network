
package com.tt.network.example.viewmodel.bean;

import android.text.TextUtils;


/**
 * Created by tengtao on 2017/3/9.
 */
public class UserInfo {

    /**
     * uid : 7
     * sort :
     * phone : 13535390021
     * password : 435052
     * nickname : 213576
     * headsmall :
     * headlarge :
     * gender : 2
     * sign :
     * province :
     * city :
     * isfriend : 0
     * isblack : 0
     * verify : 1
     * isstar : 0
     * frozen : 0
     * flow : 0M
     * captain : null
     * role : 2
     * personid :
     * remark :
     * getmsg : 0
     * fauth1 : 0
     * fauth2 : 0
     * picture1 :
     * picture2 :
     * picture3 :
     * cover :
     * lat : 22.848349
     * lng : 113.235651
     * distance : -1
     * isshop : 0
     * onship : 0
     * createtime : 1443316843
     * cbname :
     * cbflow : 0
     * flowleft : 204800
     * islimit : 0
     * flowday : 0
     * stime :
     * etime :
     * stimeStr :
     * etimeStr :
     * warn80 : 0
     * warn100 : 0
     * isby : 0
     * bydaylimit : 0.00
     * bystime :
     * bystimeStr :
     * byetime :
     * byetimeStr :
     * iuid : 0
     * token : Sc05299cdd19a0c5f0b0c47cede90cd65V
     */


    /**
     * 用户身份，2-船长，1-船员
     */
    private String identity;


    private int uid;
    private String phone;
    private transient String password;
    private String nickname;
    /**
     * 0:男，1：女，2、未填写
     */
    private int gender;
    private int iuid;
    private String token = "";
    private String ssoTicket = "";
    /**
     * 是否为登录状态
     */
    private boolean isLogined;
    private String voipPhone;
    private String voipPwd;
    /**
     * 区号
     */
    private String voipAreaCode;
    private String avatar;
    private String signature;
    private String realName;

    /**
     * 融云token
     */
    private String rongYunToken;
    //voip 使用开关 0=海上 陆地都可用 1=仅海上可用
    private int voipUseSwitch;

    //是否允许外部app更新 0=不允许 1=允许
    private String allowExternalUpdates;
    //省流量模式 1正常 2压缩图片 3无视频
    private String trafficPatterns;

    private String drPassword;
    /**
     * 是否为专属船
     */
    private String isLoginByMobile;

    /**
     * 身份认证审核状态 0不通过 1待审核 2=审核中 3=通过
     */
    private int checkStatus;
    /**
     * voip到期时间
     */
    private long voipDate;
    /**
     * voip审核状态 1未申请 0待审核 1已审核 2拒绝
     */
    private int voipApplyState;
    /**
     * voip拒绝原因
     */
    private String voipApplyRejectReason;
    /**
     * 是否需要发送验证码验证用户手机号
     */
    private boolean isRegister;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 年龄
     */
    private int age;
    /**
     * 是否订阅过天气
     * 0-没有，1-有
     */
    private int isSubscribeWeather;




    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public boolean isSubscribeWeather() {
        return isSubscribeWeather == 1;
    }

    public void setIsSubscribeWeather(int isSubscribeWeather) {
        this.isSubscribeWeather = isSubscribeWeather;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAllowExternalUpdates() {
        return allowExternalUpdates;
    }

    public long getVoipDate() {
        return voipDate;
    }

    public void setVoipDate(long voipDate) {
        this.voipDate = voipDate;
    }

    public int getVoipApplyState() {
        return voipApplyState;
    }

    public void setVoipApplyState(int voipApplyState) {
        this.voipApplyState = voipApplyState;
    }

    public String getVoipApplyRejectReason() {
        return voipApplyRejectReason;
    }

    public void setVoipApplyRejectReason(String voipApplyRejectReason) {
        this.voipApplyRejectReason = voipApplyRejectReason;
    }


    public String getIsLoginByMobile() {
        return isLoginByMobile;
    }

    public void setIsLoginByMobile(String isLoginByMobile) {
        this.isLoginByMobile = isLoginByMobile;
    }

    public String getDrPassword() {
        return drPassword + "";
    }

    public void setDrPassword(String drPassword) {
        this.drPassword = drPassword;
    }

    public boolean isAllowExternalUpdates() {
        return TextUtils.isEmpty(allowExternalUpdates) ? false : allowExternalUpdates.equals("1") ? true : false;
    }

    public void setAllowExternalUpdates(String allowExternalUpdates) {
        this.allowExternalUpdates = allowExternalUpdates;
    }

    public String getTrafficPatterns() {
        return trafficPatterns;
    }

    public void setTrafficPatterns(String trafficPatterns) {
        this.trafficPatterns = trafficPatterns;
    }

    public int getVoipUseSwitch() {
        return voipUseSwitch;
    }

    public void setVoipUseSwitch(int voipUseSwitch) {
        this.voipUseSwitch = voipUseSwitch;
    }

    public String getRongYunToken() {
        return rongYunToken;
    }

    public void setRongYunToken(String rongYunToken) {
        this.rongYunToken = rongYunToken;
    }

    public String getVoipAreaCode() {
        return voipAreaCode;
    }

    public void setVoipAreaCode(String voipAreaCode) {
        this.voipAreaCode = voipAreaCode;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRealName() {
        return TextUtils.isEmpty(realName) ? "" : realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getVoipPhone() {
        return voipPhone;
    }

    public void setVoipPhone(String voipPhone) {
        this.voipPhone = voipPhone;
    }

    public String getVoipPwd() {
        return voipPwd;
    }

    public void setVoipPwd(String voipPwd) {
        this.voipPwd = voipPwd;
    }

    public boolean isLogined() {
        return isLogined;
    }

    public void setLogined(boolean logined) {
        isLogined = logined;
    }

    public String getSsoTicket() {
        return ssoTicket;
    }

    public void setSsoTicket(String ssoTicket) {
        this.ssoTicket = ssoTicket;
    }


    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getPhone() {
        return TextUtils.isEmpty(phone) ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return TextUtils.isEmpty(nickname) ? "" : nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getDisplayName() {
        String nickName = TextUtils.isEmpty(nickname) ? realName : nickname;
        return TextUtils.isEmpty(nickName) ? phone : nickName;
    }


    /**
     * 0:男，1：女，2、未填写
     *
     * @return
     */
    public String getGender() {
        if (gender == 0) {
            return "男";
        } else if (gender == 1) {
            return "女";
        }
        return "未填写";
    }



    public void setGender(int gender) {
        this.gender = gender;
    }


    public int getIuid() {
        return iuid;
    }

    public void setIuid(int iuid) {
        this.iuid = iuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }





    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", iuid=" + iuid +
                ", token='" + token + '\'' +
                '}';
    }


    /**
     * 获取用户身份字符串
     *
     * @return
     */
    public String getIdentityOfStr() {
        String identityStr = "未知";
        if (!TextUtils.isEmpty(identity)) {
            if (identityStr.equals("1")) {
                identityStr = "船员";
            } else if (identityStr.equals("2")) {
                identityStr = "船长";
            }
        }
        return identityStr;
    }

    public String getIdCard() {
        return idCard;
    }



}
