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


module.exports = {
    sendCommande,
    sendMenuCommand,
  };