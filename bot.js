const {Discord, Client, Intents} = require('discord.js')
const {token, prefix} = require('./config.json')
const { promisify } = require('util')

const fs = require('fs')
const { voiceHandler } = require('./activities/voiceActivity/voice')
const bot = new Client({intents: [Intents.FLAGS.GUILDS, Intents.FLAGS.GUILD_MESSAGES]})
const readdir = promisify(fs.readdir)

bot.commands = new Map()

function initCommands() {
    readdir('./commands/', (error, files) => {
        if (error) throw error
        files.forEach(file => {
          if (!file.endsWith('.js')) return
          try {
            const properties = require(`./commands/${file}`)
            bot.commands.set(properties.help.name, properties)
            console.log(`Properties:  ${properties.help.name}`)
          } catch (err) {
            throw err
          }  
        })
    })
}

bot.on('ready', () => {
    initCommands()
    console.log(`Bot ${bot.user.tag} is ready`)
})

bot.on('message', (message) => {
    if(message.author === bot.user) return
    const content = message.content.toLowerCase().replace(/\s+/g, ' ').trim().split(' ')
    console.log(content)

    if(content[0] === prefix + 'stop')
        process.exit()
    
    const command = content.shift().slice(prefix.length).toLowerCase()
    const cmd = bot.commands.get(command)

    if (!cmd) return
    cmd.run(message, content, bot)
})

bot.on('voiceStateUpdate', async (oldState, newState) => {
    if(oldState.member.user.bot || newState.member.user.bot) return;

    const voice = require('../ScoreBot/activities/voiceActivity/voice')
    voice.voiceHandler(oldState, newState, bot)
})

bot.on('guildMemberAdd', member => {
  if(member.user.bot) return;

  const request = require('/HTTPRequest/request.js')
  request.post('/api/user/add', {'discordId': member.user.id, 'points': 0})
});

bot.login(token)