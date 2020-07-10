const obat = require('../model/Obat.js')
const response = require('../config/response')
const mongoose = require('mongoose')
const ObjectId = mongoose.Types.ObjectId
exports.inputDataObat = (data, gambar) =>
    new Promise(async (resolve, reject) =>{

        const obatBaru = new obat({
            KodeObat : data.KodeObat,
            NamaObat : data.NamaObat,
            JenisObat: data.JenisObat,
            Satuan: data.Satuan,
            Stok: data.Stok,
            HargaObat: data.HargaObat,
            gambar: gambar
        })

        await obat.findOne({KodeObat: data.KodeObat})
            .then(obat =>{
                if (obat){
                    reject(response.commonErrorMsg('Kode Obat Sudah Digunakan'))
                }else{
                    obatBaru.save()
                        .then(r =>{
                            resolve(response.commonSuccessMsg('Berhasil Menginput Data'))
                        }).catch(err =>{
                        reject(response.commonErrorMsg('Mohon Maaf Input Obat Gagal'))
                    })
                }
            }).catch(err =>{
                reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami'))
            })
    })

exports.lihatDataObat = () =>
    new Promise(async (resolve, reject) =>{
        await obat.find({})
            .then(result =>{
                resolve(response.commonResult(result))
            })
            .catch(()=>reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami')))
    })

exports.lihatDetailDataObat = (KodeObat) =>
    new Promise(async (resolve, reject) =>{
        await obat.findOne({KodeObat: KodeObat})
            .then(result =>{
                resolve(response.commonResult(result))
            })
            .catch(()=>reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami')))
    })

exports.updateObat = (id, data, gambar) =>
    new Promise(async (resolve, reject)=>{
        await obat.updateOne(
            {_id : ObjectId(id)},
            {
                $set: {
                    KodeObat : data.KodeObat,
                    NamaObat : data.NamaObat,
                    JenisObat: data.JenisObat,
                    Satuan: data.Satuan,
                    Stok: data.Stok,
                    HargaObat: data.HargaObat,
                    gambar: gambar
                }
            }
        ).then(obat =>{
            resolve(response.commonSuccessMsg('Berhasil Mengubah Data'))
        }).catch(err =>{
            reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami'))
        })
    })

exports.hapusobat = (_id) =>
    new Promise(async (resolve, reject) =>{
        await obat.remove({_id: ObjectId(_id)})
            .then(() =>{
                resolve(response.commonSuccessMsg('Berhasil Menghapus Data'))
            }).catch(() =>{
                reject(response.commonErrorMsg('Mohon Maaf Terjadi Kesalahan Pada Server kami'))
            })
    })