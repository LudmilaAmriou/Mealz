"use strict";

function _templateObject() {
  var data = _taggedTemplateLiteral(["\n    SELECT r.*, t.Nom_Type\n    FROM restaurant r\n    JOIN restaurant_types rt ON r.ID_Restaurant = rt.ID_Restaurant\n    JOIN type t ON rt.ID_Type = t.ID_Type\n  "]);

  _templateObject = function _templateObject() {
    return data;
  };

  return data;
}

function _taggedTemplateLiteral(strings, raw) { if (!raw) { raw = strings.slice(0); } return Object.freeze(Object.defineProperties(strings, { raw: { value: Object.freeze(raw) } })); }

var _require = require('./prismaImport'),
    prisma = _require.prisma;

function getRest() {
  return regeneratorRuntime.async(function getRest$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          return _context.abrupt("return", prisma.$queryRaw(_templateObject()));

        case 1:
        case "end":
          return _context.stop();
      }
    }
  });
}

function getRestById(id) {
  var restau;
  return regeneratorRuntime.async(function getRestById$(_context2) {
    while (1) {
      switch (_context2.prev = _context2.next) {
        case 0:
          _context2.next = 2;
          return regeneratorRuntime.awrap(prisma.restaurant.findFirst({
            where: {
              ID_Restaurant: parseInt(id, 10)
            }
          }));

        case 2:
          restau = _context2.sent;
          return _context2.abrupt("return", restau);

        case 4:
        case "end":
          return _context2.stop();
      }
    }
  });
}

module.exports = {
  getRest: getRest,
  getRestById: getRestById
};