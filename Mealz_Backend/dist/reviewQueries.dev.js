"use strict";

function _templateObject() {
  var data = _taggedTemplateLiteral(["\n    SELECT r.*, u.Nom\n    FROM rating r\n    JOIN utilisateur u ON r.ID_Utilisateur = u.ID_Utilisateur\n    WHERE ID_Restaurant = ", ";\n  "]);

  _templateObject = function _templateObject() {
    return data;
  };

  return data;
}

function _taggedTemplateLiteral(strings, raw) { if (!raw) { raw = strings.slice(0); } return Object.freeze(Object.defineProperties(strings, { raw: { value: Object.freeze(raw) } })); }

var _require = require('./prismaImport'),
    prisma = _require.prisma;

function getReviews(restId) {
  return regeneratorRuntime.async(function getReviews$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          return _context.abrupt("return", prisma.$queryRaw(_templateObject(), restId));

        case 1:
        case "end":
          return _context.stop();
      }
    }
  });
}

function sendReview(ID_User, ID_Rest, Rate, Comment) {
  var newReview;
  return regeneratorRuntime.async(function sendReview$(_context2) {
    while (1) {
      switch (_context2.prev = _context2.next) {
        case 0:
          _context2.prev = 0;
          _context2.next = 3;
          return regeneratorRuntime.awrap(prisma.rating.create({
            data: {
              ID_Utilisateur: parseInt(ID_User, 10),
              ID_Restaurant: parseInt(ID_Rest, 10),
              Rating: parseFloat(Rate),
              Commentaire: Comment
            }
          }));

        case 3:
          newReview = _context2.sent;
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
  getReviews: getReviews,
  sendReview: sendReview
};