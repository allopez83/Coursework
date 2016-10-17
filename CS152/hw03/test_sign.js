var fs = require('fs');
const crypto = require('crypto');
const data = "Data to be signed";

// Signing

const sign = crypto.createSign('RSA-SHA256');
sign.write(data);
sign.end();
const private_key = fs.readFileSync("./alicePriv.txt").toString('binary');
const sign_out = sign.sign(private_key, 'base64');
// console.log(private_key);
// console.log(sign_out);

// Verification

const verify = crypto.createVerify('RSA-SHA256');
verify.update(data);
const public_key = fs.readFileSync("./alicePub.txt").toString('binary');
const verification = verify.verify(public_key, sign_out, 'base64');
// console.log(public_key);
console.log(verification);
