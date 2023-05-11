const express = require('express');
const path = require('path');
const app = express();
const port = 3000;

// Middlewares to set view engine and set static public directory
app.use(express.static(path.join(__dirname)));

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'main.html'));
});

app.listen(port, () => {
    console.log(`App listening at http://localhost:${port}`);
});
