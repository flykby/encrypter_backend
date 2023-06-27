const { Router } = require('express');
const router = new Router();
const controller = require('./controller');

router.post("/encoding", controller.encoding);
router.post("/decoding", controller.decoding);


module.exports = router;