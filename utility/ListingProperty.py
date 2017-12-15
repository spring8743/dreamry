# -*- coding: utf-8 -*-
'''
Created on 2017年12月10日

@author: spring8743
'''

country_code = 'UK'
asin = 'B074XDBCK3'
voice_recorder_title = 'Digital Voice Recorder, DREAMRY 8GB USB Sound Audio Recorder, Voice Activated Recoder, Multifunctional Portable HD Recording Double Microphone Rechargeable Dictaphone/Noise Cancelling for lecture'


# used for keyword rank for every day
voice_recorder_keyword = [
{'digital recorder voice':'Short Keyword'},
{'digital voice recorder':'Short Keyword'},
{'digital recorders voice':'Short Keyword'},
{'voice recorder digital':'Short Keyword'},
{'recorder digital voice':'Short Keyword'}
]

# voice_recorder_keyword = [
# {'voice recorder':'Core Keyword'},
# {'audio recorder':'Core Keyword'},
# {'sound recorder':'Core Keyword'},
# {'recorder sound':'Core Keyword'},
# {'voice activated recorder':'Core Keyword'},
# {'recorder voice activated':'Core Keyword'},
# {'recorder audio':'Core Keyword'},
# {'digital recorder':'Core Keyword'},
# {'recorder digital':'Core Keyword'},
# {'digital recorder voice':'Short Keyword'},
# {'digital voice recorder':'Short Keyword'},
# {'digital recorders voice':'Short Keyword'},
# {'voice recorder digital':'Short Keyword'},
# {'recorder digital voice':'Short Keyword'},
# {'voice recorder small':'Short Keyword'},
# {'audio recorder hidden':'Short Keyword'},
# {'digital recorder audio':'Short Keyword'},
# {'digital audio recorder':'Short Keyword'},
# {'digital voice recorder 8gb':'Short Keyword'},
# {'audio recorder pc':'Short Keyword'},
# {'voice recorder with usb':'Long Tail Keyword'},
# {'audio recorder usb':'Long Tail Keyword'},
# {'hidden audio recorder voice activated':'Long Tail Keyword'},
# {'voice recorder high quality':'Long Tail Keyword'},
# {'audio recorder small':'Long Tail Keyword'},
# {'digital recorder portable':'Long Tail Keyword'},
# {'voice recorder voice activated':'Long Tail Keyword'},
# {'voice activated voice recorder':'Long Tail Keyword'},
# {'digital recorder player':'Long Tail Keyword'},
# {'voice recorder hd':'Long Tail Keyword'},
# {'voice activated usb recorder':'Long Tail Keyword'},
# {'sound recorder digital':'Long Tail Keyword'},
# {'audio recorder voice activated':'Long Tail Keyword'},
# {'digital recorder with usb':'Long Tail Keyword'},
# {'digital voice activated recorder':'Long Tail Keyword'},
# {'audio recorder microphone':'Long Tail Keyword'},
# {'digital voice recorder with usb':'Long Tail Keyword'},
# {'voice activated mini recorder':'Long Tail Keyword'},
# {'digital recorder usb':'Long Tail Keyword'},
# {'digital recorder voice activated':'Long Tail Keyword'},
# {'voice recorder mic':'Long Tail Keyword'},
# {'voice activated digital recorder':'Long Tail Keyword'},
# {'voice activated hidden recorder':'Long Tail Keyword'},
# {'digital sound recorder':'Long Tail Keyword'},
# {'sound recorder hidden':'Long Tail Keyword'},
# {'voice activated audio recorder':'Long Tail Keyword'},
# {'digital recorder with microphone':'Long Tail Keyword'},
# {'voice recorder with microphone':'Long Tail Keyword'},
# {'voice recorder for interviews':'Long Tail Keyword'},
# {'sound recorder portable':'Long Tail Keyword'}
# ]

# used for keyword click and add to cart, add to wishlist
voice_recorder_click_keyword = [
{'digital recorder voice':'Short Keyword'},
{'digital voice recorder':'Short Keyword'},
{'digital recorders voice':'Short Keyword'},
{'voice recorder digital':'Short Keyword'},
{'recorder digital voice':'Short Keyword'}
]

# used for PPC keyword click
voice_recorder_ppc_click_keyword = [
{'voice recorder small':'Short Keyword'}
]

if __name__ == '__main__':
#          voice_recorder_click_keyword = voice_recorder_click_keyword.append(voice_recorder_click_keyword)
    voice_recorder_click_keyword = voice_recorder_click_keyword * 5
    
    print voice_recorder_click_keyword.__len__()
    
    