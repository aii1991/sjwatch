import Cookies from 'js-cookie'

const userInfoKey = 'userInfo'

export function getToken() {
  return getUserInfo().token
}

export function getUid() {
  return getUserInfo().uid
}

export function getUploadToken() {
  return getUserInfo().uploadToken
}

export function getUserName() {
  return getUserInfo().userName
}

export function getNickName() {
  return getUserInfo().nickName
}

export function getRoleId() {
  return getUserInfo().roleId
}

export function getSex() {
  return getUserInfo().sex
}

export function getAge() {
  return getUserInfo().age
}

export function removeUserInfo() {
  return Cookies.remove(userInfoKey)
}

export function setUserInfo(userInfo) {
  if (typeof userInfo !== 'string') {
    userInfo = JSON.stringify(userInfo)
  }
  return Cookies.set(userInfoKey, userInfo)
}

function getUserInfo() {
  var userInfo = Cookies.get(userInfoKey)
  if (userInfo) {
    return JSON.parse(userInfo)
  }
  return {}
}
