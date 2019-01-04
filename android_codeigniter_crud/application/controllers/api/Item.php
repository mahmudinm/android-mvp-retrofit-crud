<?php

defined('BASEPATH') OR exit('No direct script access allowed');


class Item extends BD_Controller {

    function __construct()
    {
        // Construct the parent class
        parent::__construct();
        // $this->auth();
        $this->load->database();
    }

    public function index_get()
    {
        $item = $this->db->get('item')->result();
        $response['status'] = "success";
        $response['data'] = $item;
        
        $this->response($response, 200);
    }

    public function index_post()
    {
        $data = [
            'nama' => $this->post('nama'),
            'harga' => $this->post('harga')
        ];
        
        $insert = $this->db->insert('item', $data);

        if ($insert) {
            $this->response(['status' => 'success'], 200);
        } else {
            $this->response(['status' => 'fail', 502]);            
        }

    }

    public function index_put()
    {
        $id = $this->put('id');
        $data = [
            'id' => $this->put('id'),
            'nama' => $this->put('nama'),
            'harga' => $this->put('harga')
        ];

        $this->db->where('id', $id);
        $update = $this->db->update('item', $data);

        if ($update) {
            $this->response(['status' => 'success'], 200);
        } else {
            $this->response(['status' => 'fail'], 504);
        }
        
    }

    public function index_delete()
    {
        $id = $this->delete('id');

        $this->db->where('id', $id);
        $delete = $this->db->delete('item');

        if ($delete) {
            $this->response(['status' => 'success'], 201);
        } else {
            $this->response(['status' => 'fail'], 502);            
        }
    }



}
