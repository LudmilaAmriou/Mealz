"use strict";

function _templateObject2() {
  var data = _taggedTemplateLiteral(["\n      SELECT menu.*, type_menu.Nom_TMenu\n      FROM menu, type_menu\n      WHERE menu.ID_TMenu = type_menu.ID_TMenu\n        AND menu.ID_Menu = ", ";\n      ;\n      "]);

  _templateObject2 = function _templateObject2() {
    return data;
  };

  return data;
}

function _templateObject() {
  var data = _taggedTemplateLiteral(["\n          SELECT m.*, t.Nom_TMenu\n          FROM menu m\n          JOIN type_menu t ON m.ID_TMenu = t.ID_TMenu\n          WHERE m.ID_Restaurant = ", ";\n        "]);

  _templateObject = function _templateObject() {
    return data;
  };

  return data;
}

function _taggedTemplateLiteral(strings, raw) { if (!raw) { raw = strings.slice(0); } return Object.freeze(Object.defineProperties(strings, { raw: { value: Object.freeze(raw) } })); }

var _require = require('./prismaImport'),
    prisma = _require.prisma;

function findMenuByRes(restId) {
  var menus;
  return regeneratorRuntime.async(function findMenuByRes$(_context) {
    while (1) {
      switch (_context.prev = _context.next) {
        case 0:
          _context.next = 2;
          return regeneratorRuntime.awrap(prisma.$queryRaw(_templateObject(), restId));

        case 2:
          menus = _context.sent;
          return _context.abrupt("return", menus);

        case 4:
        case "end":
          return _context.stop();
      }
    }
  });
}

function findMenuDetail(MenuId) {
  var menus;
  return regeneratorRuntime.async(function findMenuDetail$(_context2) {
    while (1) {
      switch (_context2.prev = _context2.next) {
        case 0:
          _context2.next = 2;
          return regeneratorRuntime.awrap(prisma.$queryRaw(_templateObject2(), MenuId));

        case 2:
          menus = _context2.sent;
          return _context2.abrupt("return", menus);

        case 4:
        case "end":
          return _context2.stop();
      }
    }
  });
}

module.exports = {
  findMenuByRes: findMenuByRes,
  findMenuDetail: findMenuDetail
};