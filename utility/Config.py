# -*- coding: utf-8 -*-
'''
Created on 2017年12月10日

@author: spring8743
'''

# below fields are used in the GetKeywordRank.py
DOMAINS = {
    'CA':'ca',
    'DE':'de',
    'ES':'es',
    'FR':'fr',
    'IN':'in',
    'IT':'it',
    'JP':'co.jp',
    'UK':'co.uk',
    'US':'com'
    }

#the max page you want to find
max_page = 21

#Next Page Button
nextPage = '#pagnNextString'

#chrome driver location
chrome_driver = '/Users/spring8743/Documents/workspace/chromedriver'

#below field are used in the KeywordRankRobot.py
#define the empty threads list
threads = []

#define how many thread
rank_thread_num = 1

#define how many thread
click_thread_num = 1

#define how many thread
ppc_click_thread_num = 1

#proxy_url from taiyang proxy server
proxy_url = 'http://http-api.taiyangruanjian.com/getip?num=1&type=1&pro=&city=0&yys=0&port=11&pack=8955&ts=0&ys=0&cs=0&lb=1&sb=0&pb=4&mr=1&regions='

#below field are use in the KeywordClickRobot.py
keyword_click_number = 1

#below field are use in the PPCKeywordClickRobot.py
keyword_ppc_click_number = 1

#define database connection
uri = r'mysql://root:zaq12wsX@127.0.0.1/Amazon_DB?charset=utf8'

#add to cart and wish list possibility
possibility = 0.9