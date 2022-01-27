const { server_host } = require('../config.json')

module.exports.get = (url) => {

    const request = require('request')

    let p = new Promise((resolve, reject) => {
        request(server_host + url, (error, response, body) => {
            if(!error) {
                if(response.statusCode > 299 || response.statusCode < 200) {
                    console.log(body)
                }else {
                    let importantJSON = JSON.parse(body)
                    resolve(importantJSON)
                }
            }
        })
    })

    return p;
}

module.exports.post = async (url, data = {}) => {
    const axios = require('axios')

    axios.post(server_host + url, data)
        .catch((err) => {
            console.log(err)
        })

}

