#!/bin/bash
curl -H "Content-Type:application/json" -XPUT localhost:9200/person/default/1 -d '{
          "createTime" : "2019-07-08 12:56:13",
          "feature" : "8JWvu4sELjzAcxS7YI4Bu/l4lbuS/Tm7b7J0u2VeHry+EHa7FSoLu4urhLs+I5G7YCPFu+x69bs9gvs9vXzjuiS35jzWP6G7J5Rwuz+IJrtOQoq7opt9uw3CbD62mY67HmwQuws9h7u6Mqi6yG4PuxYvNj5gvBO7XleCO5SF7TtocYy7IikWu9UzjLv+nDI+dQbouZCplrtqU5K7ErLBuxXpVDp6gKg99HrFu5Xbk7t8Ma27dlKHPk59gD4VSYU7ojpju2uAc7u35IK7O22Nu9r/4ztIs8e7tYZ3u4ysyrsZKN+6H+iRujDhabt052y7udU9PLKpiLs3oyy7q6vIu4VcKT21NWC7m77oPDASubuH5rY6vv1zu8p0BbuBDoy7YW1vu40lLLv3I6S7lBNMu4wwxbvcsLe6nlgMPQBoIbqc2wY8b+alu3y4gbspAVY8e3ZQu7HsE7vpMss9z9Cmu7gtfLpfvWW7traVO+PgkLu8MdK7ohLPu9Vgt7uRgFG7AP/UPcuRgLvWDYi7Kf2OPHB4pLpE3/66eAp7uz6jPrsfbmK7MT2ju2XQILxnHie7xIQou4vnw7tjSVK7WHfJu+k3grtQ/xu75qgru7S2obuL0SU7lpYhPkUdqbnd5GC7dqXIuu3fzLo90Xo9fZMFu1JqCruZv187DyatPrgFaLslYHC7f6KVu++sPLsrUXa7gxXIu8kIILu+KTE9OqLOu1MLY7tH3j27BXBeOigweLtW6G67ylkvu9lNX7uRbQU1Dfpzu3WWl7vf3K8+JaMYu2cMVrtKqba75kt7u/vS07oYYN89J5nmuzyXUrvFSES73H5JPnNp/Lorg267YRRKuy6lEbsuTVe7Qi9Uu6CARbrwO927XQ7kup/iXTxbIyI+HHXNOdbP0bvrk3W7KifSOT2n0buTHTi793i+u2jsJj03+VK7Lg0Ru+6To7pUo3y702iSPYUCHbuBaxu7z0Wquxn7dbvjjJ+7E8Jdu+IVUrvM10C7uhMquwQ2DLvXtSS75eRRu03Omjz7ykM+xjGzu0kaMj1CWoS6eayZPn2FOzuzwEu7Yu+su63e4bovzYC7/71Ku0NV7D2ptCI6bhiVPp1pIbtBa5e75jquukqFSrsJtMe7QNKvulYNY7snDFK7V1aiu0ChgzwJQNS6iBO1u7wiurskJR0++hRIuVTkgD5vIFG7ZlmHuw5PDrtcFTC7ffnwus0h67slTay7+UxVu7W4b7s9jL27veU6PrUA6buFW0K7w2b8uuZXNbv/qBe79Ue9ukLLXz02xN085kyHu7Hpd7sB2pi7h9L4ubbOJrt0CnK7vl6Ku5YnTrs2T7u7ZWmKu+qAej0PL4C7jWAhuw==",
          "imageId" : "20190708125613-0bd5b43f-a601-4fb2-81e0-4ffff89d8328",
          "imageUrl" : "http://imagesearch-xxx.oss-cn-hangzhou.aliyuncs.com/img/person/20190708/1562561773434body.jpg",
          "online" : true,
          "personId" : "003",
          "source" : "manual",
          "type" : "body"
        }'
curl -H "Content-Type:application/json" -XPUT localhost:9200/person/default/2 -d '{
          "createTime" : "2019-11-17 10:49:57",
          "feature" : "HMG8PC2LMjsPjZu7cc2ZuwD817tvWho9qBY2u7FpxjqxatA9MFhcu1HCBjsynF+7Ob5NuwVenLvKSIO7SWW6PnKLt7uEq3I8wg/Wu4ZdsbrkW4q7VAubPbWV7ju3qwA73sTzugUb07vc37C7Pxj8uozgmLs7sWK7F/Lnu29WzjwV5wM8deaBu/zAWboHTqq71buRu80bjrsx+p+6x5SZPNbvuDxqh2C7nXEOPCLbPj1vE+q7qM48u6fxwTyS7fi64ULcu+H3lT3Qrrw9cOGKu3OdKbtH1nG7yOF+u2JStjuEp8q65YPJPbdYdj2Jfxa7xn+JuxTFtTzKqZU+QeVKu7VUXjyf9R27uTVpPMHXBLycmMO7lXzwu6BlyjgUtKS7PbnDux/w5bu9I5m7NC+Hu92/Bbwo7xM+zgN1O8R0xrq05ke75ZXSuplfj7vfAZq7cc5Cu2hbj7t7KUC7WKaluwArhj0+jX+7Op5ou5nEsLtmMwc9TxKeu0wkh7sm2ho9ZlkFvKhUKrsBlHG7qfsgPkKS3bvXppC7vmkqPkpCNbriQpq7OmKpu50IzD2KKIE8GQ6KPW3h2T1VnuW6qU2cu/9BoLteiww9cJpePv7Ty7tsfCI799cCvKYzR7iwAIA6EsyHu4EUq7uobei7MKMSu1snqjt/NNq79mIDvCNuKbwHcW27jXYBu1kuH7vVUTy7vvdnPmdIEbvEZCK7NsLLuwP1nLuKVWY7fL1WPCDrCjz/tXY7axCDu5TmKLtn0gW7AbDwPJ8dMD5gA6K7xK1EuwRDMTwDRMG6mLtYuyXhRrsNoc67JKyVuxOCsLoyq/u6MYcTu3+uT7s8s1G7nkO6u17PnbumGSE9IE+Buz6Uubm9vIi7yHpNu42Rlbu2hPw+AymMu8oFgLtUYFi73XBpPOQ9HLvZXBQ8dhIVuzaU2jxEcBa7OFMdux9nGj5RvZS7d6nHPXa7rTkYL4q7E8SfPeiH0Tx7yK274lTQPClfjrttG4i75sdju8LBvbpBKVo89/8zuo5kJT1kadC7zp6yu2wubbuSMnO7/DVZPIu9Xbv0byY6UDyhu4eVH7t5isW7WWwDPmC3nbqIGru74OsUO1bg2Ls0ZtI8KBwsu2St47qg5yq7Wd+8uyWNFj3/LVi7QscSu7OpQjkZWTI8jyiCu0YdQrwo3Ke7QaiDu40rCbvapt+7HlgpPbxKSjsQ/uq6lqCFPfG6YTtAAa27hLUUu9n+gLtPC9a7A27IuyofYj108V06xQGVu0/m4bsc/M48aY6su35wnbusoX27wshhPfjNErv+L7I8vsVcu+X5xrtIvIK7UJqXPNILyro3jrO6af/TPirFDrvvTbq62/X3uw==",
          "imageId" : "20191117104957-26c69322-80ab-44b2-9c38-533fa2e60c9a",
          "imageUrl" : "http://imagesearch-xxx.oss-cn-hangzhou.aliyuncs.com/img/person/20191117/1573958997432body.jpg",
          "online" : true,
          "personId" : "00001",
          "source" : "manual",
          "type" : "body"
        }'
