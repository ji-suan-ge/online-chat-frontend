'use strict'

import {app, BrowserWindow, ipcMain} from 'electron'

/**
 * Set `__static` path to static files in production
 * https://simulatedgreg.gitbooks.io/electron-vue/content/en/using-static-assets.html
 */
if (process.env.NODE_ENV !== 'development') {
  global.__static = require('path').join(__dirname, '/static').replace(/\\/g, '\\\\')
}

let mainWindow
const winURL = process.env.NODE_ENV === 'development'
  ? `http://localhost:9080`
  : `file://${__dirname}/index.html`

function createWindow () {
  /**
   * Initial window options
   */
  mainWindow = new BrowserWindow({
    height: 700,
    useContentSize: true,
    width: 1000,
    resizable: false,
    frame: false,
    webPreferences: {
      webSecurity: false,
      devTools: true
    }
  })

  mainWindow.loadURL(winURL)

  mainWindow.on('closed', () => {
    mainWindow = null
  })
}

app.on('ready', createWindow)

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  if (mainWindow === null) {
    createWindow()
  }
})

const ipc = ipcMain
let friendInfoWindow
ipc.on('friendInfo', function (event, data) {
  friendInfoWindow = new BrowserWindow(
    {
      height: 563,
      useContentSize: true,
      width: 410,
      resizable: false,
      movable: true,
      hasShadow: false,
      frame: false,
      transparent: true,
      webPreferences: {
        webSecurity: false,
        devTools: false
      }
    }
  )
  friendInfoWindow.loadURL(winURL + '#/friendInfo')
  friendInfoWindow.on('closed', () => { friendInfoWindow = null })
})

let infoWindow
ipc.on('selfInfo', function (event, data) {
  infoWindow = new BrowserWindow(
    {
      height: 600,
      useContentSize: true,
      width: 400,
      resizable: false,
      movable: true,
      hasShadow: false,
      frame: false,
      transparent: true,
      webPreferences: {
        webSecurity: false,
        devTools: true
      }
    }
  )
  infoWindow.loadURL(winURL + '#/selfInfo')
  infoWindow.on('closed', () => { infoWindow = null })
})

let searchFriendWindow
ipc.on('searchFriend', function (event, data) {
  if (searchFriendWindow == null) {
    searchFriendWindow = new BrowserWindow(
      {
        height: 500,
        useContentSize: true,
        width: 650,
        resizable: false,
        movable: true,
        hasShadow: false,
        frame: false,
        transparent: true,
        webPreferences: {
          webSecurity: false,
          devTools: true
        }
      }
    )
    searchFriendWindow.loadURL(winURL + '#/searchFriend')
    searchFriendWindow.on('closed', () => { searchFriendWindow = null })
  } else {
    searchFriendWindow.show()
  }
})

let searchGroupWindow
ipc.on('searchGroup', function (event, data) {
  if (searchGroupWindow == null) {
    searchGroupWindow = new BrowserWindow(
      {
        height: 500,
        useContentSize: true,
        width: 650,
        resizable: false,
        movable: true,
        hasShadow: false,
        frame: false,
        transparent: true,
        webPreferences: {
          webSecurity: false,
          devTools: false
        }
      }
    )
    searchGroupWindow.loadURL(winURL + '#/searchGroup')
    searchGroupWindow.on('closed', () => { searchGroupWindow = null })
  } else {
    searchGroupWindow.show()
  }
})

ipc.on('getAcc', (event, data) => {
  mainWindow.webContents.send('sendAcc', data)
})
ipc.on('getSelfAcc', (event, data) => {
  mainWindow.webContents.send('sendSelfAcc', data)
})
ipc.on('getAcc', (event, data) => {
  mainWindow.webContents.send('sendAcc', data)
})
ipc.on('getSelfAcc', (event, data) => {
  mainWindow.webContents.send('sendSelfAcc', data)
})
ipc.on('refresh', (event) => {
  mainWindow.webContents.send('refresh_main')
})
ipc.on('updateAvatar', (event, data) => {
  mainWindow.webContents.send('updateAvatar_main', data)
})
ipc.on('min', function (e) {
  let curWin = BrowserWindow.getFocusedWindow()
  curWin.minimize()
})
ipc.on('close', function () {
  let curWin = BrowserWindow.getFocusedWindow()
  let allWin = BrowserWindow.getAllWindows()
  if (curWin === mainWindow) {
    for (var i = 0; i < allWin.length; i++) {
      allWin[i].close()
    }
  } else {
    curWin.close()
  }
})

/**
 * Auto Updater
 *
 * Uncomment the following code below and install `electron-updater` to
 * support auto updating. Code Signing with a valid certificate is required.
 * https://simulatedgreg.gitbooks.io/electron-vue/content/en/using-electron-builder.html#auto-updating
 */

/*
import { autoUpdater } from 'electron-updater'

autoUpdater.on('update-downloaded', () => {
  autoUpdater.quitAndInstall()
})

app.on('ready', () => {
  if (process.env.NODE_ENV === 'production') autoUpdater.checkForUpdates()
})
 */
