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

#used for keyword rank thread
rank_thread_num = 1

#used for keyword click and add to cart, wish list thread
click_thread_num = 1
#used for keyword click and add to cart, wish list, how many times want to click for each keyword
keyword_click_number = 1

#used for PPC keyword click thread
ppc_click_thread_num = 1
#used for PPC keyword click,  how many times want to click for each keyword
keyword_ppc_click_number = 1

#proxy_url from taiyang proxy server
proxy_url = 'http://http-api.taiyangruanjian.com/getip?num=1&type=1&pro=&city=0&yys=0&port=11&pack=8955&ts=0&ys=0&cs=0&lb=1&sb=0&pb=4&mr=1&regions='

#define database connection
uri = r'mysql://root:zaq12wsX@127.0.0.1/Amazon_DB?charset=utf8'

#add to cart and wish list possibility
possibility = 0.9