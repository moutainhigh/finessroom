/**
 * Created by Kylan on 2018/12/16.
 */
const COURSE_URL = {
    marketShop: 'shop/getMarketShopList', //获取门店
    checkAgreement: 'frAgreement/checkCardAgreement', //检查协议编号
    addAgreement: 'frAgreement/addCardAgreement', //检查协议编号
    marketUserList: 'personnelInfo/getMarketUserList', //获取销售人员
    fetchSdaduim: 'shop/ShopSdaduimList', //选择场馆
    fetchTechnician: 'frCustomerCourse/getMarketUserList', // 获取技师，助教
    fetchUserInfo: 'frClient/getClient', //获取用户信息，根据用户Id
    getOrderNo: 'frCardOrderInfo/getOrderNo', //获取订单编号
    verifyPersonInfo: 'personnelInfo/getVerification', //用户授权验证
};