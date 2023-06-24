const { prisma } = require('./prismaImport');

async function sendCommande(Adresse_livraison,Prix_Tolal,ID_Utilisateur) {
    try{
        const newCommand = await prisma.commande.create({
          data: {
            Adresse_livraison: Adresse_livraison,
            Prix_Tolal: Prix_Tolal,
            ID_Utilisateur: parseInt(ID_Utilisateur,10)
          },
        });
        return parseInt(newCommand.ID_Commande,10);
      }catch(error){
        console.error('Error sending review:', error);
        return 0;
      }
  }
async function sendMenuCommand(ID_Commande,ID_Menu,Size,Quantite,Notes,ID_Restaurant ){
  try{
    const newCommand = await prisma.commande_menu.create({
      data: {
        ID_Commande: ID_Commande,
        ID_Menu: parseInt(ID_Menu,10),
        Size: parseInt(Size,10),
        Quantite:parseInt(Quantite,10),
        Notes: Notes,
        ID_Restaurant: parseInt(ID_Restaurant,10)
      },
    });
    return true;
  }catch(error){
    console.error('Error sending review:', error);
    return false;
  }
}

async function getOrders(userId) {
    return prisma.$queryRaw`
    SELECT c.ID_Commande, c.Adresse_livraison, c.Prix_Tolal, r.Nom, GROUP_CONCAT(DISTINCT m.Nom) AS NomMs, m.Prix_unitare, cm.Quantite
    FROM commande c
    JOIN commande_menu cm ON c.ID_Commande = cm.ID_Commande
    JOIN menu m ON cm.ID_Menu = m.ID_Menu
    JOIN restaurant r ON m.ID_Restaurant = r.ID_Restaurant
    WHERE c.ID_Utilisateur = ${userId}
    GROUP BY c.ID_Commande;
  `;
  }

module.exports = {
    sendCommande,
    sendMenuCommand,
    getOrders
  };