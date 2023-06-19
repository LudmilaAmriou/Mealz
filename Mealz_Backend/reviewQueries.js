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
        ID_Utilisateur: parseInt(ID_User,10),
        ID_Restaurant: parseInt(ID_Rest,10),
        Rating: parseFloat(Rate),
        Commentaire:Comment,
      },
    });
    return true;
  }catch(error){
    console.error('Error sending review:', error);
    return false;
  }
}


module.exports = {
    getReviews,
    sendReview,
  };