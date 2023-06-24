"use strict";

var _require = require('./Auth'),
    comparePasswordWithEmail = _require.comparePasswordWithEmail;

var _require2 = require('./restaurantQueries'),
    getRest = _require2.getRest,
    getRestById = _require2.getRestById;

var _require3 = require('./reviewQueries'),
    getReviews = _require3.getReviews,
    sendReview = _require3.sendReview;

var _require4 = require('./menuQueries'),
    findMenuByRes = _require4.findMenuByRes,
    findMenuDetail = _require4.findMenuDetail;

var _require5 = require('./commandeQueries'),
    sendCommande = _require5.sendCommande,
    sendMenuCommand = _require5.sendMenuCommand,
    getOrders = _require5.getOrders;

var express = require('express');

var bodyParser = require('body-parser');

var _require6 = require('./prismaImport'),
    prisma = _require6.prisma;

var _require7 = require('./utilisateurQueries'),
    insertUser = _require7.insertUser;

var _require8 = require('./notification'),
    sendNotification = _require8.sendNotification;

var app = express();

var bcrypt = require('bcrypt');

app.use(bodyParser.json()); // Define an endpoint to get all restaurants

app.get('/restaus/getall', function _callee(req, res) {
  var restaus;
  return regeneratorRuntime.async(function _callee$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          _context.prev = 0;
          _context.next = 3;
          return regeneratorRuntime.awrap(getRest());

        case 3:
          restaus = _context.sent;
          res.json(restaus); //sendNotification()

          _context.next = 11;
          break;

        case 7:
          _context.prev = 7;
          _context.t0 = _context["catch"](0);
          console.error(_context.t0);
          res.status(500).json({
            error: 'An error occurred while fetching restaurants'
          });

        case 11:
        case "end":
          return _context.stop();
      }
    }
  }, null, null, [[0, 7]]);
}); // Get menus of restaurant

app.get('/menus/:restaurantId', function _callee2(req, res) {
  var restaurantId, menu;
  return regeneratorRuntime.async(function _callee2$(_context2) {
    while (1) {
      switch (_context2.prev = _context2.next) {
        case 0:
          _context2.prev = 0;
          restaurantId = req.params.restaurantId; // console.log(restaurantId);

          _context2.next = 4;
          return regeneratorRuntime.awrap(findMenuByRes(restaurantId));

        case 4:
          menu = _context2.sent;
          res.json(menu);
          _context2.next = 12;
          break;

        case 8:
          _context2.prev = 8;
          _context2.t0 = _context2["catch"](0);
          console.error(_context2.t0);
          res.status(500).json({
            error: 'An error occurred while fetching menus'
          });

        case 12:
        case "end":
          return _context2.stop();
      }
    }
  }, null, null, [[0, 8]]);
}); // Get menu details

app.get('/menu/:menuId', function _callee3(req, res) {
  var menuId, menuDetails;
  return regeneratorRuntime.async(function _callee3$(_context3) {
    while (1) {
      switch (_context3.prev = _context3.next) {
        case 0:
          _context3.prev = 0;
          menuId = req.params.menuId;
          _context3.next = 4;
          return regeneratorRuntime.awrap(findMenuDetail(menuId));

        case 4:
          menuDetails = _context3.sent;
          console.log(menuDetails[0]);
          res.json(menuDetails[0]);
          _context3.next = 13;
          break;

        case 9:
          _context3.prev = 9;
          _context3.t0 = _context3["catch"](0);
          console.error(_context3.t0);
          res.status(500).json({
            error: 'An error occurred while fetching menu details'
          });

        case 13:
        case "end":
          return _context3.stop();
      }
    }
  }, null, null, [[0, 9]]);
}); // Get rest by Id

app.get('/restau/:restaurantId', function _callee4(req, res) {
  var restaurantId, restau;
  return regeneratorRuntime.async(function _callee4$(_context4) {
    while (1) {
      switch (_context4.prev = _context4.next) {
        case 0:
          _context4.prev = 0;
          restaurantId = req.params.restaurantId;
          _context4.next = 4;
          return regeneratorRuntime.awrap(getRestById(restaurantId));

        case 4:
          restau = _context4.sent;
          res.json(restau);
          _context4.next = 12;
          break;

        case 8:
          _context4.prev = 8;
          _context4.t0 = _context4["catch"](0);
          console.error(_context4.t0);
          res.status(500).json({
            error: 'An error occurred while fetching restaus'
          });

        case 12:
        case "end":
          return _context4.stop();
      }
    }
  }, null, null, [[0, 8]]);
}); // LogIn

app.post('/login', function _callee5(req, res) {
  var _req$body, mail, password, comparisonResult;

  return regeneratorRuntime.async(function _callee5$(_context5) {
    while (1) {
      switch (_context5.prev = _context5.next) {
        case 0:
          _req$body = req.body, mail = _req$body.mail, password = _req$body.password; //console.log(req.body);
          // Compare the password with the stored hashed password

          _context5.next = 3;
          return regeneratorRuntime.awrap(comparePasswordWithEmail(mail, password));

        case 3:
          comparisonResult = _context5.sent;
          // Send the comparison result back to Kotlin
          res.json(comparisonResult);

        case 5:
        case "end":
          return _context5.stop();
      }
    }
  });
}); //Sign Up

app.post('/signup', function _callee6(req, res) {
  var saltRounds, _req$body2, username, email, password, address, hashedPassword, comparisonResult;

  return regeneratorRuntime.async(function _callee6$(_context6) {
    while (1) {
      switch (_context6.prev = _context6.next) {
        case 0:
          saltRounds = 10;
          _req$body2 = req.body, username = _req$body2.username, email = _req$body2.email, password = _req$body2.password, address = _req$body2.address;
          _context6.next = 4;
          return regeneratorRuntime.awrap(bcrypt.hash(password, saltRounds));

        case 4:
          hashedPassword = _context6.sent;
          _context6.next = 7;
          return regeneratorRuntime.awrap(insertUser(username, email, hashedPassword, address));

        case 7:
          comparisonResult = _context6.sent;
          // Send the comparison result back to Kotlin
          res.json(comparisonResult);

        case 9:
        case "end":
          return _context6.stop();
      }
    }
  });
}); // Get the reviews

app.get('/reviews/:restaurantId', function _callee7(req, res) {
  var restaurantId, reviews;
  return regeneratorRuntime.async(function _callee7$(_context7) {
    while (1) {
      switch (_context7.prev = _context7.next) {
        case 0:
          _context7.prev = 0;
          restaurantId = req.params.restaurantId;
          _context7.next = 4;
          return regeneratorRuntime.awrap(getReviews(restaurantId));

        case 4:
          reviews = _context7.sent;
          //  console.log(reviews);
          res.json(reviews);
          _context7.next = 12;
          break;

        case 8:
          _context7.prev = 8;
          _context7.t0 = _context7["catch"](0);
          console.error(_context7.t0);
          res.status(500).json({
            error: 'An error occurred while fetching reviews'
          });

        case 12:
        case "end":
          return _context7.stop();
      }
    }
  }, null, null, [[0, 8]]);
}); // Send reviews

app.post('/review', function _callee8(req, res) {
  var _req$body3, ID_Utilisateur, ID_Restaurant, Rating, Commentaire, newrev;

  return regeneratorRuntime.async(function _callee8$(_context8) {
    while (1) {
      switch (_context8.prev = _context8.next) {
        case 0:
          _req$body3 = req.body, ID_Utilisateur = _req$body3.ID_Utilisateur, ID_Restaurant = _req$body3.ID_Restaurant, Rating = _req$body3.Rating, Commentaire = _req$body3.Commentaire;
          console.log(req.body);
          _context8.next = 4;
          return regeneratorRuntime.awrap(sendReview(ID_Utilisateur, ID_Restaurant, Rating, Commentaire));

        case 4:
          newrev = _context8.sent;
          //console.log(newrev);
          // Send the result back to Kotlin
          res.json(newrev);

        case 6:
        case "end":
          return _context8.stop();
      }
    }
  });
}); // Send command

app.post('/commande', function _callee9(req, res) {
  var _req$body4, Adresse_livraison, Prix_Tolal, ID_Utilisateur, newcom;

  return regeneratorRuntime.async(function _callee9$(_context9) {
    while (1) {
      switch (_context9.prev = _context9.next) {
        case 0:
          _req$body4 = req.body, Adresse_livraison = _req$body4.Adresse_livraison, Prix_Tolal = _req$body4.Prix_Tolal, ID_Utilisateur = _req$body4.ID_Utilisateur;
          _context9.next = 3;
          return regeneratorRuntime.awrap(sendCommande(Adresse_livraison, Prix_Tolal, ID_Utilisateur));

        case 3:
          newcom = _context9.sent;
          console.log(newcom); // Send the result back to Kotlin

          res.json(newcom);

        case 6:
        case "end":
          return _context9.stop();
      }
    }
  });
}); // Send menu command

app.post('/commandemenu', function _callee10(req, res) {
  var _req$body5, ID_Commande, ID_Menu, Size, Quantite, Notes, ID_Restaurant, newcom;

  return regeneratorRuntime.async(function _callee10$(_context10) {
    while (1) {
      switch (_context10.prev = _context10.next) {
        case 0:
          _req$body5 = req.body, ID_Commande = _req$body5.ID_Commande, ID_Menu = _req$body5.ID_Menu, Size = _req$body5.Size, Quantite = _req$body5.Quantite, Notes = _req$body5.Notes, ID_Restaurant = _req$body5.ID_Restaurant;
          _context10.next = 3;
          return regeneratorRuntime.awrap(sendMenuCommand(ID_Commande, ID_Menu, Size, Quantite, Notes, ID_Restaurant));

        case 3:
          newcom = _context10.sent;
          console.log(newcom); // Send the result back to Kotlin

          res.json(newcom);

        case 6:
        case "end":
          return _context10.stop();
      }
    }
  });
}); //Get orders 

app.get('/orders/:userId', function _callee11(req, res) {
  var userId, Orders;
  return regeneratorRuntime.async(function _callee11$(_context11) {
    while (1) {
      switch (_context11.prev = _context11.next) {
        case 0:
          _context11.prev = 0;
          userId = req.params.userId;
          _context11.next = 4;
          return regeneratorRuntime.awrap(getOrders(userId));

        case 4:
          Orders = _context11.sent;
          //  console.log(reviews);
          res.json(Orders);
          _context11.next = 12;
          break;

        case 8:
          _context11.prev = 8;
          _context11.t0 = _context11["catch"](0);
          console.error(_context11.t0);
          res.status(500).json({
            error: 'An error occurred while fetching orders'
          });

        case 12:
        case "end":
          return _context11.stop();
      }
    }
  }, null, null, [[0, 8]]);
});
app.get('/', function _callee12(req, res) {
  return regeneratorRuntime.async(function _callee12$(_context12) {
    while (1) {
      switch (_context12.prev = _context12.next) {
        case 0:
          res.send('Hello World!');

        case 1:
        case "end":
          return _context12.stop();
      }
    }
  });
}); // Start the server

app.listen(4004, function () {
  console.log('Server is running on port 4004');
});