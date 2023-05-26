const bcrypt = require('bcrypt');
const { prisma } = require('./prismaImport');

const {findUserByMail,insertUser } = require('./utilisateurQueries');


// Function to compare the received password with the stored hashed password
async function comparePasswordWithEmail(email, password) {
  try {
    // Retrieve the stored hashed password from the database
    const user = await findUserByMail(email);
    console.log(user)
  
    if (!user) {
      // User not found, login failed
      return { success: false, ID_Utilisateur:null,message: "This user doesn't exit or wrong email" };
    }
    const storedHashedPassword = user.Password;
    // Compare the received password with the stored hashed password
    const passwordsMatch = await bcrypt.compare(password, storedHashedPassword);
    console.log(passwordsMatch)
   
    if (passwordsMatch) {
      // Passwords match, login successful
      return { success: true,ID_Utilisateur:user.ID_Utilisateur ,message: 'Login successful' };
    } else {
      // Passwords do not match, login failed
      return { success: false,ID_Utilsateur:user.ID_Utilisateur, message: 'Incorrect password, Try again!' };
    }
  } catch (error) {
    // Error occurred
    console.error('Error comparing passwords:', error);
    return { success: false,  ID_Utilisateur:null,message: 'An error occurred' };
  }
}

// ADD TEST USER TO DB
// const idUser = 1;
// const name = 'test';
// const email = 'test@gmail.com';
// const address = 'El biar';
// const password = "test";

// insertUser(idUser, name,'',email, address, password)
//   .then((newUser) => {
//     console.log('New user inserted:', newUser);
//   })
//   .catch((error) => {
//     console.error('Error inserting user:', error);
//   });


  module.exports = {
    comparePasswordWithEmail,
  };