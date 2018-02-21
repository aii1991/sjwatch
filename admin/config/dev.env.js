'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"https://easy-mock.com/mock/5a7811b245c9a13f770d65c6/rest/v1"',
  BASE_DOWNLOAD_URL: '"http://p43e9mjtu.bkt.clouddn.com"',
  FILE_UPLOAD_URL: '"http://upload-z2.qiniup.com"'
})
