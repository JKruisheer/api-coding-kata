# API Coding Kata
Code repository for developing a new method of assessing someone's skills through an API-based kata.

In a small town called Axina, a shop owner which goes by the name of Paul intends to commence selling tools online. HIs shop is called Tooltopia. Having operated a physical store for over 25 years, he recognizes the current demand for online shopping and has decided to establish an online store.

## Backend Engineer
As a backend engineer, you're making an API. The shop owner knows a bit about this from the supermarket. He understands it helps put info into the database made by his nephew. In Paul's world, security isn't a big worry. They don't think hacking is real. So, they're not too concerned about keeping our endpoint safe. No extra protection, like filtering or hashing, they're okay just sending passwords online.

### Lets get things started
Your first job is to make an authorization API. It needs a way for people to sign up and log in. If you want to make Paul happy, you can add a way to delete, but that's not needed. When someone signs up, the API should give them a personID. It should work when you send this type of request:

    {
        "name": "Paul",
        "age": 59,
        "address": "Axxonia street 1",
        "postalCode": "67592",
        "username": "Paul123",
        "password": "password"
    }
This should run on localhost:8080/tooltopia/registration/register

We also need a login. It takes a username and password and gives back the person's ID if everything's correct. But if the password's wrong or the username isn't known, it should say 'no entry.' Here's how the JSON could look:

    {
        "username": "Paul",
        "password": "password"
    }
This should run on localhost:8080/tooltopia/registration/login

**Remember, the password and username need to be written exactly as they were when created. Upper and lower case letters matter.**

### Our first orders
Now that we have a registration system, it is time to process our first orders. Please create an endpoint which runs at localhost:8080/tooltopia/order and it should accept:

    {
        "product": "Hammer",
        "quantity": 2,
        "price": 2.00,
        "billing_address": Tooltopia 12,
        "shipping_address": Tooltopia 13,
        "personID": 1
    }

personID should refer to the person that we created in the authorization API.

### Summarize
After one month of sales, Paul now wants to know how much revenue he has made over the past months. Can you make an endpoint which gives back the revenue per month?

### Inventory
Up until now we assumed that we always had inventory for the first order. We need an endpoint which returns the inventory that we still have of the current items. We also need an endpoint which gets the inventory size of the ID of the item we requested.

We also need to add some validation to the order endpoint to check if we even have that in stock! Assume that the product field is the reference to the inventory.


//TODO determine if this is correct
When your endpoint is finished, put it in a docker container and send it to the test server. Then, run the scripts and let's check if everything works!

## frontend engineer