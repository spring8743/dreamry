# -*- coding: utf-8 -*-
'''
Created on 2017年12月2日

@author: spring8743
'''

import threading
from GetKeywordRank import get_product_page_by_list
from utility.ListingProperty import *
from random import shuffle
from utility.Config import*


#define how many keywords you want to run in one thread
words_num = voice_recorder_keyword.__len__() / rank_thread_num 

#shuffle the keywords type
voice_recorder_keyword_new = voice_recorder_keyword[:] # Copy keywords
shuffle(voice_recorder_keyword) # Shuffle keywords

# thread worker function
def worker(startPoint, endPoint, country_code, asin):
#     print 'worker:%s'%num
    get_product_page_by_list(voice_recorder_keyword[startPoint:endPoint], voice_recorder_title, country_code, asin)
    return 


for i  in range(rank_thread_num ):
    #define the start point and end point for each thread
    startPoint = i * words_num
    endPoint = startPoint + words_num
    
    #If it's last page then end pint is None
    if i == rank_thread_num -1 :
        endPoint = None
    
    #start the thread
    t = threading.Thread(target=worker, args =(startPoint, endPoint, country_code, asin,))
    threads.append(t)
    t.start()


