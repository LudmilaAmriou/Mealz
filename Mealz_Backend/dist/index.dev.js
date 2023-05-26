"use strict";

var _require = require('./Auth'),
    comparePasswordWithEmail = _require.comparePasswordWithEmail;

var _require2 = require('./restaurantQueries'),
    getRest = _require2.getRest;

var _require3 = require('./menuQueries'),
    findMenuByRes = _require3.findMenuByRes,
    findMenuDetail = _require3.findMenuDetail;

var express = require('express');

var bodyParser = require('body-parser');

var _require4 = require('./prismaImport'),
    prisma = _require4.prisma;

var _require5 = require('./utilisateurQueries'),
    insertUser = _require5.insertUser;

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
          res.json(restaus);
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
          menuId = req.params.menuId; //console.log(menuId);

          _context3.next = 4;
          return regeneratorRuntime.awrap(findMenuDetail(menuId));

        case 4:
          menuDetails = _context3.sent;
          res.json(menuDetails);
          _context3.next = 12;
          break;

        case 8:
          _context3.prev = 8;
          _context3.t0 = _context3["catch"](0);
          console.error(_context3.t0);
          res.status(500).json({
            error: 'An error occurred while fetching menu details'
          });

        case 12:
        case "end":
          return _context3.stop();
      }
    }
  }, null, null, [[0, 8]]);
}); // LogIn

app.post('/login', function _callee4(req, res) {
  var _req$body, mail, password, comparisonResult;

  return regeneratorRuntime.async(function _callee4$(_context4) {
    while (1) {
      switch (_context4.prev = _context4.next) {
        case 0:
          _req$body = req.body, mail = _req$body.mail, password = _req$body.password; //console.log(req.body);
          // Compare the password with the stored hashed password

          _context4.next = 3;
          return regeneratorRuntime.awrap(comparePasswordWithEmail(mail, password));

        case 3:
          comparisonResult = _context4.sent;
          // Send the comparison result back to Kotlin
          res.json(comparisonResult);

        case 5:
        case "end":
          return _context4.stop();
      }
    }
  });
}); //Sign Up

app.post('/signup', function _callee5(req, res) {
  var saltRounds, _req$body2, username, email, password, address, hashedPassword, comparisonResult;

  return regeneratorRuntime.async(function _callee5$(_context5) {
    while (1) {
      switch (_context5.prev = _context5.next) {
        case 0:
          saltRounds = 10;
          _req$body2 = req.body, username = _req$body2.username, email = _req$body2.email, password = _req$body2.password, address = _req$body2.address;
          _context5.next = 4;
          return regeneratorRuntime.awrap(bcrypt.hash(password, saltRounds));

        case 4:
          hashedPassword = _context5.sent;
          console.log(req.body); // Compare the password with the stored hashed password

          _context5.next = 8;
          return regeneratorRuntime.awrap(insertUser(username, email, hashedPassword, address));

        case 8:
          comparisonResult = _context5.sent;
          // Send the comparison result back to Kotlin
          res.json(comparisonResult);

        case 10:
        case "end":
          return _context5.stop();
      }
    }
  });
});
app.get('/', function _callee6(req, res) {
  return regeneratorRuntime.async(function _callee6$(_context6) {
    while (1) {
      switch (_context6.prev = _context6.next) {
        case 0:
          res.send('Hello World!');

        case 1:
        case "end":
          return _context6.stop();
      }
    }
  });
}); // Start the server

app.listen(4004, function () {
  console.log('Server is running on port 4004');
});