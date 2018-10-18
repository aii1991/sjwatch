const getters = {
  sidebar: state => state.app.sidebar,
  token: state => state.user.token,
  avatar: state => 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3051523273,1778200958&fm=26&gp=0.jpg',
  name: state => state.user.name,
  nickName: state => state.user.nickName,
  roleId: state => state.user.roleId,
  uploadToken: state => state.user.uploadToken
}
export default getters
