const express = require('express');

const os = require('os');
const dns = require('dns');

const cors = require('cors');

const settings = require('./config/settings');

const app = express();

app.use(cors());
app.use(express.json());
app.use('/api0', require('./routes/router'));

const { HOST, PORT } = settings;


const start = async () => {
  try {
    app.listen(PORT, HOST, () => {
        dns.lookup(os.hostname(), function (error, host) {
          if (error) {
            console.log(error);
          } else {
            console.log(`📎 Network:  http://${host}:${PORT}`);
            console.log(`📎 Localhost:  http://localhost:${PORT}`);
          }
        })
    })

  } catch (e) {
    console.log(e);
  }
}

start()
