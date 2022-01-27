const request = require('../../HTTPRequest/request.js')

module.exports.voiceHandler = async (oldState, newState, bot) => {
    let newUserChannel = newState.channel
    let oldUserChannel = oldState.channel

    let nowDate = new Date()

    request.get(`/api/points/${newState.member.user.id}`)
    if (oldUserChannel === null && newUserChannel !== null) {
        let user = newState.member.user
        request.post(`/api/voice?discordId=${user.id}`, {'joinDate': nowDate})
    } else if (oldUserChannel !== null && newUserChannel === null) {
        let user = oldState.member.user
        request.post(`/api/voice/left?discordId=${user.id}&timeStamp=${nowDate.getTime().toString()}`, {})
    } else {
        // let user = oldState.member.user
        // request.post(`/api/voice/left?discordId=${user.id}&leftDate=${nowDate.getTime()}`, {})
    }

}