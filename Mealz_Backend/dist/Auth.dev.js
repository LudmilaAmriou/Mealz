"use strict";

var bcrypt = require('bcrypt');

var _require = require('./prismaImport'),
    prisma = _require.prisma;

var _require2 = require('./utilisateurQueries'),
    findUserByMail = _require2.findUserByMail,
    insertUser = _require2.insertUser; // Function to compare the received password with the stored hashed password


function comparePasswordWithEmail(email, password) {
  var user, storedHashedPassword, passwordsMatch;
  return regeneratorRuntime.async(function comparePasswordWithEmail$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          _context.prev = 0;
          _context.next = 3;
          return regeneratorRuntime.awrap(findUserByMail(email));

        case 3:
          user = _context.sent;
          console.log(user);

          if (user) {
            _context.next = 7;
            break;
          }

          return _context.abrupt("return", {
            success: false,
            ID_Utilisateur: null,
            message: "This user doesn't exit or wrong email"
          });

        case 7:
          storedHashedPassword = user.Password; // Compare the received password with the stored hashed password

          _context.next = 10;
          return regeneratorRuntime.awrap(bcrypt.compare(password, storedHashedPassword));

        case 10:
          passwordsMatch = _context.sent;
          console.log(passwordsMatch);

          if (!passwordsMatch) {
            _context.next = 16;
            break;
          }

          return _context.abrupt("return", {
            success: true,
            ID_Utilisateur: user.ID_Utilisateur,
            message: 'Login successful'
          });

        case 16:
          return _context.abrupt("return", {
            success: false,
            ID_Utilsateur: user.ID_Utilisateur,
            message: 'Incorrect password, Try again!'
          });

        case 17:
          _context.next = 23;
          break;

        case 19:
          _context.prev = 19;
          _context.t0 = _context["catch"](0);
          // Error occurred
          console.error('Error comparing passwords:', _context.t0);
          return _context.abrupt("return", {
            success: false,
            ID_Utilisateur: null,
            message: 'An error occurred'
          });

        case 23:
        case "end":
          return _context.stop();
      }
    }
  }, null, null, [[0, 19]]);
} // ADD TEST USER TO DB
// const idUser = 1;
// const name = 'test';
// const email = 'test@gmail.com';
// const address = 'El biar';
// const password = "test";
// insertUser(idUser, name,'',email, address, password)
//   .then((newUser) => {
//     console.log('New user inserted:', newUser);
//   })
//   .catch((error) => {
//     console.error('Error inserting user:', error);
//   });


module.exports = {
  comparePasswordWithEmail: comparePasswordWithEmail
};