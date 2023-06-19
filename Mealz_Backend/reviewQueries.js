const { prisma } = require('./prismaImport');

async function getReviews(restId) {
    return prisma.$queryRaw`
    SELECT r.*, u.Nom
    FROM rating r
    JOIN utilisateur u ON r.ID_Utilisateur = u.ID_Utilisateur
    WHERE ID_Restaurant = ${restId};
  `;
  }
async function sendReview(ID_User,ID_Rest,Rate,Comment){
  try{
    const newReview = await prisma.rating.create({
      data: {
        ID_Utilisateur: ID_User,
        ID_Restaurant: ID_Rest,
        Rating: Rate,
        Commentaire:Comment,
      },
    });
    return true;
  }catch{
    return false;
  }
}


module.exports = {
    getReviews,
    sendReview,
  };