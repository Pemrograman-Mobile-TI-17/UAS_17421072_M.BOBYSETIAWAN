const mongoose = require('mongoose');

const userSchema = mongoose.Schema({

    KodeObat: {
        type: String
    },
    NamaObat: {
        type: String
    },
    JenisObat: {
        type: String
    },
    Satuan: {
        type: String
    },
    Stok: {
        type: String
    },
    HargaObat: {
        type: String
    },
    gambar: {
        type: String
    }
})
module.exports = mongoose.model('obat', userSchema)