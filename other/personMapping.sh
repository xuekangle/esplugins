#!/bin/bash
curl -H "Content-Type:application/json" -XPUT http://localhost:9200/_template/person_template -d '
{
    "order": 0,
    "template": "person",
    "settings": {
      "index": {
        "number_of_shards": "5",
        "number_of_replicas": "0"
      }
    },
    "mappings": {
      "default": {
        "properties": {
          "imageId": {
            "type": "keyword",
            "doc_values": false
          },
          "feature": {
            "type": "binary",
            "doc_values": true,
            "index":false
          },
          "imageUrl": {
            "type": "keyword",
            "doc_values": false
          },
          "online": {
            "type": "boolean"
          },
          "personId": {
            "type": "text"
          },
          "source": {
            "type": "keyword",
            "doc_values": false
          },
          "type": {
            "type": "keyword",
            "doc_values": false
          }
        }
      }
    },
    "aliases": {}
  }'
