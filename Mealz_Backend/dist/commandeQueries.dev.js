"use strict";

var _require = require('./prismaImport'),
    prisma = _require.prisma;

function sendCommande(Adresse_livraison, Prix_Tolal, ID_Utilisateur) {
  var newCommand;
  return regeneratorRuntime.async(function sendCommande$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          _context.prev = 0;
          _context.next = 3;
          return regeneratorRuntime.awrap(prisma.commande.create({
            data: {
              Adresse_livraison: Adresse_livraison,
              Prix_Tolal: Prix_Tolal,
              ID_Utilisateur: parseInt(ID_Utilisateur, 10)
            }
          }));

        case 3:
          newCommand = _context.sent;
          return _context.abrupt("return", parseInt(newCommand.ID_Commande, 10));

        case 7:
          _context.prev = 7;
          _context.t0 = _context["catch"](0);
          console.error('Error sending review:', _context.t0);
          return _context.abrupt("return", 0);

        case 11:
        case "end":
          return _context.stop();
      }
    }
  }, null, null, [[0, 7]]);
}

function sendMenuCommand(ID_Commande, ID_Menu, Size, Quantite, Notes, ID_Restaurant) {
  var newCommand;
  return regeneratorRuntime.async(function sendMenuCommand$(_context2) {
    while (1) {
      switch (_context2.prev = _context2.next) {
        case 0:
          _context2.prev = 0;
          _context2.next = 3;
          return regeneratorRuntime.awrap(prisma.commande_menu.create({
            data: {
              ID_Commande: ID_Commande,
              ID_Menu: parseInt(ID_Menu, 10),
              Size: parseInt(Size, 10),
              Quantite: parseInt(Quantite, 10),
              Notes: Notes,
              ID_Restaurant: parseInt(ID_Restaurant, 10)
            }
          }));

        case 3:
          newCommand = _context2.sent;
          return _context2.abrupt("return", true);

        case 7:
          _context2.prev = 7;
          _context2.t0 = _context2["catch"](0);
          console.error('Error sending review:', _context2.t0);
          return _context2.abrupt("return", false);

        case 11:
        case "end":
          return _context2.stop();
      }
    }
  }, null, null, [[0, 7]]);
}

module.exports = {
  sendCommande: sendCommande,
  sendMenuCommand: sendMenuCommand
};