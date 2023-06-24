const { prisma } = require('./prismaImport');



async function findMenuByRes(restId) {
        const menus = await prisma.$queryRaw`
          SELECT m.*, t.Nom_TMenu
          FROM menu m
          JOIN type_menu t ON m.ID_TMenu = t.ID_TMenu
          WHERE m.ID_Restaurant = ${restId};
        `;
        return menus;
      }
async function findMenuDetail(MenuId) {
      const menus = await prisma.$queryRaw`
      SELECT menu.*, type_menu.Nom_TMenu
      FROM menu, type_menu
      WHERE menu.ID_TMenu = type_menu.ID_TMenu
        AND menu.ID_Menu = ${MenuId};
      ;
      `;
      return menus;
}
  
  module.exports = {
    findMenuByRes,
    findMenuDetail,
  };