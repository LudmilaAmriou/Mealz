"use strict";

function _templateObject() {
  var data = _taggedTemplateLiteral(["\n    SELECT c.ID_Commande, c.Adresse_livraison, c.Prix_Tolal, r.Nom, GROUP_CONCAT(DISTINCT m.Nom) AS NomMs, m.Prix_unitare, cm.Quantite\n    FROM commande c\n    JOIN commande_menu cm ON c.ID_Commande = cm.ID_Commande\n    JOIN menu m ON cm.ID_Menu = m.ID_Menu\n    JOIN restaurant r ON m.ID_Restaurant = r.ID_Restaurant\n    WHERE c.ID_Utilisateur = ", "\n    GROUP BY c.ID_Commande;\n  "]);

  _templateObject = function _templateObject() {
    return data;
  };

  return data;
}

function _taggedTemplateLiteral(strings, raw) { if (!raw) { raw = strings.slice(0); } return Object.freeze(Object.defineProperties(strings, { raw: { value: Object.freeze(raw) } })); }

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

function getOrders(userId) {
  return regeneratorRuntime.async(function getOrders$(_context3) {
    while (1) {
      switch (_context3.prev = _context3.next) {
        case 0:
          return _context3.abrupt("return", prisma.$queryRaw(_templateObject(), userId));

        case 1:
        case "end":
          return _context3.stop();
      }
    }
  });
}

module.exports = {
  sendCommande: sendCommande,
  sendMenuCommand: sendMenuCommand,
  getOrders: getOrders
};