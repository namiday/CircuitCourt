
var express = require('express');
var hostname = 'ec2-34-247-50-129.eu-west-1.compute.amazonaws.com';
var port = 3000; 
var mongoose = require('mongoose'); 
var options = { server: { socketOptions: { keepAlive: 300000, connectTimeoutMS: 30000 } }, 
replset: { socketOptions: { keepAlive: 300000, connectTimeoutMS : 30000 } } };
var urlmongo = "mongodb://charly:password@" + hostname + ":27017/circuitcourt"; 
mongoose.connect(urlmongo, options);
var db = mongoose.connection; 
db.on('error', console.error.bind(console, 'Erreur lors de la connexion')); 
db.once('open', function (){
    console.log("Connexionnn à la base OK"); 
}); 
var app = express(); 
var bodyParser = require("body-parser"); 
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(function (req, res, next) {

    // Website you wish to allow to connect
    res.setHeader('Access-Control-Allow-Origin', '*');

    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');

    // Request headers you wish to allow
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader('Access-Control-Allow-Credentials', true);

    // Pass to next layer of middleware
    next();
});
var myRouter = express.Router(); 

var usersSchema = mongoose.Schema({
    _id: Number,
    mail: String,
    password: String,
    firstName: String,
    lastName: String,
    nickName: String,
    isSeller: Boolean,
    shopName: String,
    shopCity: String,
    shopStreet: String,
    shopNum: Number,
    shopPostalCode: Number,
    shopKind: String,
    shopDescription: String,
    contacts: String,
    phone: String,
    shopLatitude: Number,
    shopLongitude: Number

}); 
var Users = mongoose.model('users', usersSchema); 

var productsSchema = mongoose.Schema({
    _id: Number,
	shopId: Number,
	name: String,
	price: Number,
	tags: String,
	type: String
}); 
var Products = mongoose.model('products', productsSchema); 

//Route par défaut
myRouter.route('/')
.get(function(req,res){ 
    res.json("Bienvenue sur notre API!"); 
});

//Récupération des utilisateurs
myRouter.route('/getUsers')
.get(function(req,res){ 
    Users.find(function(err, users){
        if (err){
            res.send(err); 
        }
        res.json(users); 
    }); 
});

//Récupération des IDs utilisateurs
myRouter.route('/getUsersId')
.get(function(req,res){ 
    Users.find(function(err, users){
        if (err){
            res.send(err); 
        }
		
		var data = [];
		
		for(var i = 0; i < users.length; i++){
			data.push(users[i]._id);
        } 
        res.json(data); 
    }); 
});

//Récupération des IDs produits
myRouter.route('/getProductsId')
.get(function(req,res){ 
    Products.find(function(err, products){
        if (err){
            res.send(err); 
        }
		
		var data = [];
		
		for(var i = 0; i < products.length; i++){
			data.push(products[i]._id);
        } 
        res.json(data); 
    }); 
});

//Création d'un utilisateur seller
myRouter.route('/createUser/:mail/:password/:firstName/:lastName/:nickName/:shopName/:shopCity/:shopStreet/:shopNum/:shopPostalCode/:shopKind/:shopDescription/:contacts/:phone/:shopLongitude/:shopLatitude')
.get(function(req,res){ 
    var user = new Users();
	var random = Math.floor(Math.random() * Math.floor(10000000));
	user._id = random;
	user.mail = req.params.mail;
	user.password = req.params.password;
	user.firstName = req.params.firstName;
	user.lastName = req.params.lastName;
	user.nickName = req.params.nickName;
	user.isSeller = true;
	user.shopName = req.params.shopName;
	user.shopCity = req.params.shopCity;
	user.shopStreet = req.params.shopStreet;
	user.shopNum = random;
	user.shopPostalCode = parseInt(req.params.shopPostalCode);
	user.shopKind = req.params.shopKind;
	user.shopDescription = req.params.shopDescription;
	user.contacts = req.params.contacts;
	user.phone = req.params.phone;
	user.shopLatitude = parseFloat(req.params.shopLatitude);
	user.shopLongitude = parseFloat(req.params.shopLongitude);
	user.save(function(err){
		if(err){
			res.send(err);
		}
		res.json({message : 'Bravo, le user est maintenant stockée en base de données'});
	});               
});

//Création d'un utilisateur
myRouter.route('/createUser/:mail/:password/:firstName/:lastName/:nickName')
.get(function(req,res){ 
    var user = new Users();
	user._id = Math.floor(Math.random() * Math.floor(10000000));
	user.mail = req.params.mail;
	user.password = req.params.password;
	user.firstName = req.params.firstName;
	user.lastName = req.params.lastName;
	user.nickName = req.params.nickName;
	user.isSeller = false;
	user.save(function(err){
		if(err){
			res.send(err);
		}
		res.json({message : 'Bravo, le user est maintenant stockée en base de données'});
	});               
});

//Création d'un produit
myRouter.route('/createProduct/:shop_id/:name/:price/:tags/:type')
.get(function(req,res){ 
    var product = new Products();
	product._id = Math.floor(Math.random() * Math.floor(10000000));
	product.shopId = req.params.shop_id;
	product.name = req.params.name;
	product.price = req.params.price;
	product.tags = req.params.tags;
	product.type = req.params.type;
	product.save(function(err){
		if(err){
			res.send(err);
		}
		res.json({message : 'Bravo, le product est maintenant stockée en base de données'});
	});               
});

/*
//Récupération des utilisateurs
myRouter.route('/test')
.get(function(req,res){ 
    Users.find(function(err, users){
        if (err){
            res.send(err); 
        }
        res.json(getLastUserId()); 
    }); 
});

function getLastUserId(){
	var lastId = 0;
	
    Users.find(function(err, users){
        if (err){
            res.send(err); 
        }
		for(var i = 0; i < users.length; i++){
			if(lastId < users[i]._id){
				lastId = users[i]._id
			}
        }
		console.log(users);
        //res.json(lastId); 
    });
	
	return lastId;
}
*/

//Mise à jour d'un utilisateur seller
myRouter.route('/updateUser/:user_id/:mail/:password/:firstName/:lastName/:nickName/:shopName/:shopCity/:shopStreet/:shopNum/:shopPostalCode/:shopKind/:shopDescription/:contacts/:phone/:shopLongitude/:shopLatitude')
.get(function(req,res){ 
    Users.findById(req.params.user_id, function(err, user) {
	    if (err){
	        res.send(err);
	    }
		user.mail = req.params.mail;
		user.password = req.params.password;
		user.firstName = req.params.firstName;
		user.lastName = req.params.lastName;
		user.nickName = req.params.nickName;
		user.isSeller = true;
		user.shopName = req.params.shopName;
		user.shopCity = req.params.shopCity;
		user.shopStreet = req.params.shopStreet;
		user.shopNum = req.params.shopNum;
		user.shopPostalCode = parseInt(req.params.shopPostalCode);
		user.shopKind = req.params.shopKind;
		user.shopDescription = req.params.shopDescription;
		user.contacts = req.params.contacts;
		user.phone = req.params.phone;
		user.shopLongitude = parseFloat(req.params.shopLongitude);
		user.shopLatitude = parseFloat(req.params.shopLatitude);
		user.save(function(err){
			if(err){
				res.send(err);
			}
			res.json({message : 'Bravo, mise à jour des données OK'});
		});                
    });
});

//Mise à jour d'un utilisateur
myRouter.route('/updateUser/:user_id/:mail/:password/:firstName/:lastName/:nickName')
.get(function(req,res){ 
    Users.findById(req.params.user_id, function(err, user) {
	    if (err){
	        res.send(err);
	    }
		user.mail = req.params.mail;
		user.password = req.params.password;
		user.firstName = req.params.firstName;
		user.lastName = req.params.lastName;
		user.nickName = req.params.nickName;
		user.isSeller = false;
		user.save(function(err){
			if(err){
				res.send(err);
			}
			res.json({message : 'Bravo, mise à jour des données OK'});
		});                
    });
});

//Mise à jour d'un produit
myRouter.route('/updateProduct/:product_id/:shop_id/:name/:price/:tags/:type')
.get(function(req,res){ 
    Products.findById(req.params.product_id, function(err, product) {
	    if (err){
	        res.send(err);
	    }
		product.shopId = req.params.shop_id;
		product.name = req.params.name;
		product.price = req.params.price;
		product.tags = req.params.tags;
		product.type = req.params.type;
		product.save(function(err){
			if(err){
				res.send(err);
			}
			res.json({message : 'Bravo, mise à jour des données OK'});
		});                
    });
});

//Suppression d'un utilisateur
myRouter.route('/deleteUser/:user_id')
.get(function(req,res){ 

 	Users.remove({_id: req.params.user_id}, function(err, user){
        if (err){
            res.send(err); 
        }
        res.json({message:"Bravo, utilisateur supprimé"}); 
    });    
});

//Suppression d'un produit
myRouter.route('/deleteProduct/:product_id')
.get(function(req,res){ 

 	Products.remove({_id: req.params.product_id}, function(err, product){
        if (err){
            res.send(err); 
        }
        res.json({message:"Bravo, produit supprimé"}); 
    });    
});

//Passage d'un utilisateur en shop
myRouter.route('/upgrade/:user_id')
.put(function(req,res){ 
    Users.findById(req.params.user_id, function(err, user) {
	    if (err){
	        res.send(err);
	    }
		user.isSeller = true;
		user.save(function(err){
			if(err){
				res.send(err);
			}
			res.json({message : 'Bravo, mise à jour des données OK'});
		});                
    });
});

//Annulation du shop d'un utilisateur
myRouter.route('/downgrade/:user_id')
.put(function(req,res){ 
    Users.findById(req.params.user_id, function(err, user) {
	    if (err){
	        res.send(err);
	    }
		user.isSeller = false;
		user.save(function(err){
			if(err){
				res.send(err);
			}
			res.json({message : 'Bravo, mise à jour des données OK'});
		});                
    });
});

//Récupération d'un utilisateur en fonction de son mail
myRouter.route('/getUser/:user_mail')
.get(function(req,res){ 
    Users.find(function(err, users){
        if (err){
            res.send(err); 
        }
        var user;
		var data;
		var userFound = false;
		
        for(var i = 0; i < users.length; i++){
        	if(users[i].mail == req.params.user_mail){
				user = users[i];
				userFound = true;
				break;
        	}
        }
		
		if(userFound){
			data = {
				id: user._id,
				mail: user.mail,
				password: user.password,
				firstName: user.firstName,
				lastName: user.lastName,
				nickName: user.nickName,
				isSeller: user.isSeller,
				response: 1
			}
			if(user.isSeller){
				data["shopName"] = user.shopName;
				data["shopCity"] = user.shopCity;
				data["shopStreet"] = user.shopStreet;
				data["shopNum"] = user.shopNum;
				data["shopPostalCode"] = user.shopPostalCode;
				data["shopKind"] = user.shopKind;
				data["shopDescription"] = user.shopDescription;
				data["contacts"] = user.contacts;
				data["phone"] = user.phone;
				data["shopLongitude"] = user.shopLongitude;
				data["shopLatitude"] = user.shopLatitude;
				
			}
		}
		else{
			data = {
				response: 0
			}
		}
		res.json(data);
    });
})

//Récupération de tous les produits
myRouter.route('/getProducts')
.get(function(req,res){ 
    Products.find(function(err, products){
        if (err){
            res.send(err); 
        }
        res.json(products); 
    }); 
})

//Création d'un produit
myRouter.route('/createProduct')
.post(function(req,res){ 
    var product = new Products();
	product._id = Math.floor(Math.random() * Math.floor(10000000));
	product.shopId = 9636999;
	product.name = "product";
	product.price = 34;
	product.tags = "tag1, tag2";
	product.type = "legume";
	product.save(function(err){
		if(err){
			res.send(err);
		}
		res.json({message : 'Bravo, le product est maintenant stockée en base de données'});
	});               
});


//Récupération des produits d'un shop en fonction de l'id
myRouter.route('/getProducts/:shop_id')
.get(function(req,res){ 
    Products.find(function(err, products){
        if (err){
            res.send(err); 
        }
        var data = [];
        for(var i = 0; i < products.length; i++){
        	if(products[i].shopId == req.params.shop_id){
        		data.push(products[i]);
        	}
        }
        res.json(data); 
    });
})

//Récupération des utilisateurs qui sont des shop dans un certain rayon
myRouter.route('/getShops/:longitude/:latitude/:rayon')
.get(function(req,res){ 

    Users.find(function(err, users){
        if (err){
            res.send(err); 
        }
        var data = [];
		var object;
		var minOneShop = false;
        for(var i = 0; i < users.length; i++){
        	users[i].password = null;
        	if(users[i].isSeller == true){
        		req.params.latitude = parseFloat(req.params.latitude);
        		req.params.longitude = parseFloat(req.params.longitude);
        		req.params.rayon = parseFloat(req.params.rayon);
				
				console.log("\n\n");
				console.log("mail=" + users[i].mail);
				console.log("currentLocation: currentLat=" + req.params.latitude + ", currentLong=" + req.params.longitude);
				console.log("shopLocation: shopLat=" + users[i].shopLatitude + ", shopLong=" + users[i].shopLongitude);
				//console.log("");
				
				console.log("dist=" + getDistanceFromLatLonInKm(req.params.latitude,req.params.longitude,users[i].shopLatitude,users[i].shopLongitude));
				
        		if(getDistanceFromLatLonInKm(req.params.latitude,req.params.longitude,users[i].shopLatitude,users[i].shopLongitude) <= req.params.rayon){
					object = {
						shopName: users[i].shopName,
						shopCity: users[i].shopCity,
						shopStreet: users[i].shopStreet,
						shopPostalCode: users[i].shopPostalCode,
						shopNum: users[i].shopNum,
						shopDescription: users[i].shopDescription,
						contacts: users[i].contacts,
						phone: users[i].phone,
						shopLongitude: users[i].shopLongitude,
						shopLatitude: users[i].shopLatitude,
						response: 1
					}
					minOneShop = true;
					data.push(object);
        		}
        	}
        }
		
		if(!minOneShop){
			object = {
				response: 0
			}
			data.push(object);
		}
        res.json(data); 


    });
})

function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {

	var R = 6371; // Radius of the earth

	var latDistance = deg2rad(lat2 - lat1);
	var lonDistance = deg2rad(lon2 - lon1);
	var a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
			+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
			* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	var dist = R * c;
	console.log('dist=' + dist);
	return dist;
}

function deg2rad(deg) {
	return deg * (Math.PI/180)
}

function rad2deg(rad) {
	return (rad * 180.0 / Math.PI)
}

//Récupération d'un shop grâce à son numéro
myRouter.route('/getShop/:number')
.get(function(req,res){ 

    Users.find(function(err, users){
        if (err){
            res.send(err); 
        }		
		var shop;
		var data;
		var shopFound = false;
		
        for(var i = 0; i < users.length; i++){
        	if(users[i].shopNum == req.params.number){
			shop = users[i];
			shopFound = true;
			break;
        	}
        }
		
		if(shopFound){
			data = {
				shopName: shop.shopName,
				shopCity: shop.shopCity,
				shopStreet: shop.shopStreet,
				shopPostalCode: shop.shopPostalCode,
				shopDescription: shop.shopDescription,
				shopNum: shop.shopNum,
				contacts: shop.contacts,
				phone: shop.phone,
				shopLongitude: shop.shopLongitude,
				shopLatitude: shop.shopLatitude,
				response: 1
			}
		}
		else{
			data = {
				response: 0
			}
		}
		res.json(data);
    });
})

app.use(myRouter);   
app.listen(port, hostname, function(){
	console.log("Mon serveur fonctionne sur http://"+ hostname +":"+port); 
});
