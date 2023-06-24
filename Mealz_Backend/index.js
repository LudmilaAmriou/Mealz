const {comparePasswordWithEmail} = require('./Auth');
const { getRest,getRestById } = require('./restaurantQueries');
const { getReviews,sendReview } = require('./reviewQueries');
const { findMenuByRes,findMenuDetail } = require('./menuQueries');
const { sendCommande,sendMenuCommand } = require('./commandeQueries');
const express = require('express');
const bodyParser = require('body-parser');
const {prisma} = require('./prismaImport')
const { insertUser } = require('./utilisateurQueries');
const app = express();
const bcrypt = require('bcrypt');


app.use(bodyParser.json());
// Define an endpoint to get all restaurants
app.get('/restaus/getall', async (req, res) => {
  try {
    const restaus = await getRest();
    res.json(restaus);
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'An error occurred while fetching restaurants' });
  }
});


// Get menus of restaurant
app.get('/menus/:restaurantId', async(req, res) => {
  try{
    const restaurantId = req.params.restaurantId;
    // console.log(restaurantId);
    const menu = await findMenuByRes(restaurantId);
  
    res.json(menu);
} catch (error) {
    console.error(error);
    res.status(500).json({ error: 'An error occurred while fetching menus' });
}
});
// Get menu details
app.get('/menu/:menuId', async(req, res) => {
  try{
    const menuId = req.params.menuId;
    const menuDetails = await findMenuDetail(menuId);
    console.log(menuDetails[0])
    res.json(menuDetails[0]);
} catch (error) {
    console.error(error);
    res.status(500).json({ error: 'An error occurred while fetching menu details' });
}
});
// Get rest by Id
app.get('/restau/:restaurantId', async(req, res) => {
  try{
    const restaurantId = req.params.restaurantId;
    const restau = await getRestById(restaurantId);
  
    res.json(restau);
} catch (error) {
    console.error(error);
    res.status(500).json({ error: 'An error occurred while fetching restaus' });
}
});

// LogIn
app.post('/login', async (req, res) => {
 const { mail, password } = req.body;
 //console.log(req.body);
  // Compare the password with the stored hashed password
  const comparisonResult = await comparePasswordWithEmail(mail, password);

  // Send the comparison result back to Kotlin
  res.json(comparisonResult);
});

//Sign Up
app.post('/signup', async (req, res) => {
  const saltRounds = 10;
  
  const { username, email, password, address } = req.body;
  const hashedPassword = await bcrypt.hash(password, saltRounds);
  //console.log(req.body);
   // Compare the password with the stored hashed password
   const comparisonResult = await insertUser(username,email,hashedPassword,address);
 
   // Send the comparison result back to Kotlin
   res.json(comparisonResult);
 });
 
// Get the reviews
app.get('/reviews/:restaurantId', async (req, res) => {
  try {
    const restaurantId = req.params.restaurantId;
    const reviews = await getReviews(restaurantId);
   //  console.log(reviews);
    res.json(reviews);
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'An error occurred while fetching restaurants' });
  }
});

// Send reviews
app.post('/review', async (req, res) => {
 
  
  const { ID_Utilisateur, ID_Restaurant, Rating, Commentaire } = req.body;
  console.log(req.body);
   const newrev = await sendReview(ID_Utilisateur,ID_Restaurant,Rating,Commentaire);
  //console.log(newrev);
   // Send the result back to Kotlin
   res.json(newrev);
 });
 // Send command
 app.post('/commande', async (req, res) => {
 
  
  const {Adresse_livraison,Prix_Tolal,ID_Utilisateur} = req.body;
   const newcom = await sendCommande(Adresse_livraison,Prix_Tolal,ID_Utilisateur);
  console.log(newcom);
   // Send the result back to Kotlin
   res.json(newcom);
 });

 // Send menu command
 app.post('/commandemenu', async (req, res) => {
  const {ID_Commande, ID_Menu,Size, Quantite,	Notes,ID_Restaurant} = req.body;
   const newcom = await sendMenuCommand(ID_Commande, ID_Menu,Size, Quantite,	Notes,ID_Restaurant);
  console.log(newcom);
   // Send the result back to Kotlin
   res.json(newcom);
 });

app.get('/', async (req, res) => {
  res.send('Hello World!')
  });
  
// Start the server
app.listen(4004, () => {
  console.log('Server is running on port 4004');
});
