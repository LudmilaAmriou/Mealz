"use strict";

var _require = require('./prismaImport'),
    prisma = _require.prisma;

function getUsers() {
  return regeneratorRuntime.async(function getUsers$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          return _context.abrupt("return", prisma.utilisateur.findMany());

        case 1:
        case "end":
          return _context.stop();
      }
    }
  });
}

function findUserByMail(mail) {
  var user;
  return regeneratorRuntime.async(function findUserByMail$(_context2) {
    while (1) {
      switch (_context2.prev = _context2.next) {
        case 0:
          _context2.next = 2;
          return regeneratorRuntime.awrap(prisma.utilisateur.findFirst({
            where: {
              Mail: mail
            }
          }));

        case 2:
          user = _context2.sent;
          return _context2.abrupt("return", user);

        case 4:
        case "end":
          return _context2.stop();
      }
    }
  });
}

function insertUser(username, email, password, address) {
  var newUser;
  return regeneratorRuntime.async(function insertUser$(_context3) {
    while (1) {
      switch (_context3.prev = _context3.next) {
        case 0:
          _context3.prev = 0;
          _context3.next = 3;
          return regeneratorRuntime.awrap(prisma.utilisateur.create({
            data: {
              Nom: username,
              Mail: email,
              Password: password,
              Adresse: address
            }
          }));

        case 3:
          newUser = _context3.sent;
          return _context3.abrupt("return", {
            success: true,
            ID_Utilisateur: newUser.ID_Utilisateur,
            message: 'User signed up successfully'
          });

        case 7:
          _context3.prev = 7;
          _context3.t0 = _context3["catch"](0);
          return _context3.abrupt("return", {
            success: false,
            ID_Utilisateur: 0,
            message: 'Sign Up failed'
          });

        case 10:
        case "end":
          return _context3.stop();
      }
    }
  }, null, null, [[0, 7]]);
}

module.exports = {
  getUsers: getUsers,
  findUserByMail: findUserByMail,
  insertUser: insertUser
};