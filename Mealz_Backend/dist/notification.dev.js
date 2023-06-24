"use strict";

var admin = require("firebase-admin");

require('dotenv').config();

var serviceAccount = require("./serviceAccountKey.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
}); /// Function to send a notification

function sendNotification() {
  // Define the message payload
  var message = {
    notification: {
      title: 'Etat',
      body: 'On vous prépare votre commande ! Plus que quelques minutes restantes'
    },
    token: process.env.TOKEN // The FCM device token of the recipient device

  }; // Send the message

  admin.messaging().send(message).then(function (response) {
    console.log('Successfully sent notification:', response);
  })["catch"](function (error) {
    console.error('Error sending notification:', error);
  });
}

module.exports = {
  sendNotification: sendNotification
};