const axios = require('axios');

const HOST = 'http://0.0.0.0';
const PORT_GO = 3002;
const PORT_JAVA = 3001;

class controller {

    async encoding(req, res) {
        try {
            const {numberEncrypt, message, key} = req.body;
            const axReq = await axios.post(`${HOST}:${numberEncrypt > 4 ? PORT_GO : PORT_JAVA}/api0/encrypted`, {
                numberEncrypt: numberEncrypt,
                message: message,
                key: key
            });

            res.json({
                message: axReq.data
            });
            return;
        } catch (e) {
            res.json({status: false, message: "Ой! Сервер не работает"})
            console.log(e);
            return;
        }

    }

    async decoding(req, res) {
        try {
            const {numberEncrypt, message, key} = req.body;
            const axReq = await axios.post(`${HOST}:${numberEncrypt > 4 ? PORT_GO : PORT_JAVA}/api0/decrypted`, {
                numberEncrypt: numberEncrypt,
                message: message,
                key: key
            });
            
            res.json({
                message: axReq.data
            });
            return;

        } catch (e) {
            res.json({status: false, message: "Ой! Сервер не работает"})
            console.log(e);
            return;
        }

    }

}

module.exports = new controller();