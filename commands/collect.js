module.exports.run = async (message, content, bot) => {
    
    bot.user.setActivity("Activity test")
    console.log(message.author.presence.activities)
}

module.exports.help = {
    name: 'collect',
    description: 'Collect points!',
    usage: '/collect'
}