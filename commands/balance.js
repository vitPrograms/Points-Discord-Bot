const {Discord} = require('discord.js')
const request = require('../HTTPRequest/request.js')

module.exports.run = async (message, content, bot) => {
    
    const sender = message.mentions.users.first() || message.author;

    request.get(`/api/points/${sender.id}`)
    .then(data => {
        message.channel.send(`${sender.username} has _<${data}>_ points`)
    })
}

module.exports.help = {
    name: 'balance',
    description: 'Display your balance!',
    usage: '/balance (option: <@username>)'
}
