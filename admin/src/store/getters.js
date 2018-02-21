const getters = {
  sidebar: state => state.app.sidebar,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  nickName: state => state.user.nickName,
  roleId: state => state.user.roleId,
  uploadToken: state => state.user.uploadToken
}
export default getters
