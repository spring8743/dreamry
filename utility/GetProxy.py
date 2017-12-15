# -*- coding: utf-8 -*-
'''
Created on 2017年12月10日

@author: spring8743
'''

import requests
import time
from Config import *

def get_proxy():
    try:
        #sleep for 5 secs, when there is multiple thread sometimes there will be time out issue, can't get the proxy ip
        time.sleep(5)
        request = requests.get(proxy_url)
        proxy = request.content
        return proxy
    except Exception:
        print Exception.message

