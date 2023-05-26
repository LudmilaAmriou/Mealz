const { prisma } = require('./prismaImport');


async function getUsers() {
    return prisma.utilisateur.findMany();
  }
  async function findUserByMail(mail) {
    const user = await prisma.utilisateur.findFirst({
      where: {
        Mail: mail ,
      },
    });
    return user;
  }
  async function insertUser(username, email,password,address) {
          
    try{
    const newUser = await prisma.utilisateur.create({
      data: {
        Nom: username,
        Mail: email,
        Password: password,
        Adresse:address,
      },
    });
    return { success: true,ID_Utilisateur:newUser.ID_Utilisateur ,message: 'User signed up successfully' };
  }catch{
    return { success: false,ID_Utilisateur:0 ,message: 'Sign Up failed' };
  }
}
module.exports = {
    getUsers,
    findUserByMail,
    insertUser,
  };
