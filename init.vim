syntax on
set tabstop=8
set softtabstop=0
set expandtab
set shiftwidth=4
set smarttab

set relativenumber

set mouse=a

" remaps
nnoremap ; <Esc>:

nnoremap  <c-o> <Esc>:CtrlPBuffer<Enter>
nnoremap ` :ls<Return>
nnoremap <S-i> ix<Esc>r  


" vimplug
call plug#begin('~/.local/share/nvim/plugs')

Plug 'ctrlpvim/ctrlp.vim'
Plug 'vim-airline/vim-airline'
Plug 't9md/vim-choosewin'

Plug 'Shougo/deoplete.nvim'
Plug 'Shougo/neocomplete'
"Plug 'Shougo/echodoc.vim'
Plug 'Shougo/vimproc.vim', {'do' : 'make'}

Plug 'tpope/vim-commentary'
Plug 'Raimondi/delimitMate'
Plug 'rhysd/committia.vim'

Plug 'davidhalter/jedi-vim'
Plug 'zchee/deoplete-jedi'

Plug 'vim-python/python-syntax'
Plug 'tmhedberg/SimpylFold'
Plug 'vim-scripts/python_match.vim'

Plug 'mboughaba/i3config.vim'

Plug 'dylanaraps/wal.vim'
Plug 'rafi/awesome-vim-colorschemes'

call plug#end()

set mouse=a
set background=dark

" python stuff
let g:python_highlight_all = 1

" tab swap windows
nmap <Tab> <C-w>w
nmap <S-Tab> <C-w>W

" ctrl swap tabs
nnoremap <C-Tab> :tabnext<CR>
nnoremap <C-S-Tab> :tabprevious<CR>

" choosewin
nmap - <Plug>(choosewin)
let g:choosewin_overlay_enable=1

" ctrlp
let g:ctrlp_map='<c-p>'
let g:ctrlp_cmd='CtrlP'
let g:ctrlp_working_path_mode='ra'

" tab control 
nnoremap nt :tabnew<CR>
nnoremap dt :tabclose<CR>

vnoremap // y/<C-R>"<CR>
vnoremap <C-Z> <Esc><C-W><C-W>
nnoremap <C-N> <Esc>:call ToggleComment()<Return>

" Insert new line & return to normal
nnoremap <CR> o<Esc>

" window controls
nnoremap <silent> <C-k> :wincmd k<CR>
nnoremap <silent> <C-j> :wincmd j<CR>
nnoremap <silent> <C-h> :wincmd h<CR>
nnoremap <silent> <C-l> :wincmd l<CR>

" ctrlp stuff
let g:ctrlp_open_new_file = 't'
let g:ctrlp_working_path_mode = 'ra' 
let g:ctrlp_map = '<c-p>'
let g:ctrlp_cmd = 'CtrlP'
nnoremap <C-o> :CtrlPBuffer<CR>
"nnoremap <C-l> :CtrlPMRU<CR>

" airline stuff
"

"colo onedark 
"colo happy_hacking 
colo wal

"hi Normal guibg=NONE ctermbg=NONE

set laststatus=2
"let g:airline_theme='deep_space'
let g:airline_theme = 'wal'

let g:airline_powerline_fonts = 1

let g:airline#extensions#tabline#enabled = 1 
let g:airline#extensions#tabline#show_close_button = 0 
let g:airline#extensions#tabline#tab_nr_type = 0
"let g:airline#extensions#tabline#left_sep = ''
"let g:airline#extensions#tabline#right_sep = ''
"let g:airline#extensions#tabline#left_alt_sep = ''
"let g:airline#extensions#tabline#right_alt_sep = ''
"let g:airline#extensions#tabline#show_tab_type = 0
let g:airline#extensions#tabline#formatter = 'unique_tail'
let g:airline#extensions#tabline#show_buffers = 1
let g:airline#extensions#tabline#buffer_idx_mode = 1
nmap <leader>1 <Plug>AirlineSelectTab1
nmap <leader>2 <Plug>AirlineSelectTab2
nmap <leader>3 <Plug>AirlineSelectTab3
nmap <leader>4 <Plug>AirlineSelectTab4
nmap <leader>5 <Plug>AirlineSelectTab5
nmap <leader>6 <Plug>AirlineSelectTab6
nmap <leader>7 <Plug>AirlineSelectTab7
nmap <leader>8 <Plug>AirlineSelectTab8
nmap <leader>9 <Plug>AirlineSelectTab9
nmap <leader>- <Plug>AirlineSelectPrevTab
nmap <leader>+ <Plug>AirlineSelectNextTab

"if !exists('g:airline_symbols')
"    let g:airline_left_sep=''
"    let g:airline_right_sep=''
"endif


function ToggleComment()
let s:save_y = @y
normal 0^2"yyl
if @y == "/*"
    normal 0^3x$xxx
else
    execute "normal 0^i/* \<Esc>$a */\<Esc>"
endif
let @y = s:save_y
unlet s:save_y
endfunction


