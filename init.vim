" add comment here
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
Plug 'Shougo/echodoc.vim'
Plug 'Shougo/vimproc.vim', {'do' : 'make'}

Plug 'Shougo/denite.nvim'
Plug 'nixprime/cpsm'

Plug 'tpope/vim-commentary'
Plug 'Raimondi/delimitMate'
Plug 'rhysd/committia.vim'

Plug 'scrooloose/nerdtree'
Plug 'jballanc/nerdtree-space-keys'

Plug 'xolox/vim-session'
Plug 'xolox/vim-misc'

Plug 'davidhalter/jedi-vim'
Plug 'zchee/deoplete-jedi'

Plug 'vim-python/python-syntax'
Plug 'tmhedberg/SimpylFold'
Plug 'vim-scripts/python_match.vim'

"Plug 'mboughaba/i3config.vim'

"Plug 'dylanaraps/wal.vim'
Plug 'rafi/awesome-vim-colorschemes'

call plug#end()

set mouse=a
"set background=dark

let g:session_directory = '~/.cache/nvim/sessions'

" deoplete
let g:deoplete#enable_at_startup = 1
let g:deoplete#enable_smart_case = 1
let g:deoplete#auto_complete_delay = 50
let g:deoplete#auto_complete_start_length = 1

" nerdtree
let g:NERDTreeMinimalUI = 1
let g:NERDTreeWinSize = 25
let g:NERDTreeCascadeOpenSingleChildDir = 1
let g:NERDTreeCascadeSingleChildDir = 0
let g:NERDTreeShowHidden = 1
let g:NERDTreeRespectWildIgnore = 0
let g:NERDTreeAutoDeleteBuffer = 0
let g:NERDTreeQuitOnOpen = 1
let g:NERDTreeHijackNetrw = 1
" let g:NERDTreeBookmarksFile = $VARPATH.'/treemarks'
let NERDTreeIgnore = [
	\ '\.git$', '\.hg$', '\.svn$', '\.stversions$', '\.pyc$', '\.svn$',
	\ '\.DS_Store$', '\.sass-cache$', '__pycache__$', '\.egg-info$', '\.cache$'
	\ ]



" Double leader key for toggling visual-line mode
nmap <silent> <Leader>v V
vmap <Leader><Leader> <Esc>

" Session management shortcuts
nnoremap <silent>cc :CloseSession<CR>
nnoremap <silent>co :OpenSession<CR>
nnoremap <silent>cs :SaveSession<CR>
nnoremap <silent>cr :RestartVim<CR>
nnoremap <silent>cd :DeleteSession<CR>

map \\ :NERDTreeToggle<CR>

let g:session_autosave='yes'
let g:session_autoload='no'
set sessionoptions-=buffers


let mapleader = "\<Space>" 
nnoremap ! :!

cmap W!! w !sudo tee % >/dev/null

let g:cpsm_match_empty_query = 0

" python stuff
let g:python_highlight_all = 1

" Drag current line/s vertically and auto-indent
vnoremap mk :m-2<CR>gv=gv
vnoremap mj :m'>+<CR>gv=gv
noremap  mk :m-2<CR>
noremap  mj :m+<CR>

" Focus the current fold by closing all others
nnoremap zf zMza

" tab swap windows
nmap <Tab> <C-w>w
nmap <S-Tab> <C-w>W

" Change current word in a repeatable manner
nnoremap cn *``cgn
nnoremap cN *``cgN

" Change selected word in a repeatable manner
vnoremap <expr> cn "y/\\V\<C-r>=escape(@\", '/')\<CR>\<CR>" . "``cgn"
vnoremap <expr> cN "y/\\V\<C-r>=escape(@\", '/')\<CR>\<CR>" . "``cgN"

nnoremap cp yap<S-}>p
nnoremap <leader>a =ip

" Tabs
nnoremap <silent> g0 :<C-u>tabfirst<CR>
nnoremap <silent> g$ :<C-u>tablast<CR>
nnoremap <silent> gr :<C-u>tabprevious<CR>
nnoremap <silent> gd :<C-u>tabclose<CR>
nnoremap <silent> <A-k> :<C-U>tabnext<CR>
nnoremap <silent> <A-j> :<C-U>tabprevious<CR>
nnoremap <silent> <C-Tab> :<C-U>tabnext<CR>
nnoremap <silent> <C-S-Tab> :<C-U>tabprevious<CR>
" Uses g:lasttab set on TabLeave in MyAutoCmd
let g:lasttab = 1

" ctrl swap tabs
nnoremap <C-Tab> :tabnext<CR>
nnoremap <C-S-Tab> :tabprevious<CR>

nnoremap <silent><Leader>w :write<CR>
vnoremap <silent><Leader>w <Esc>:write<CR>

nnoremap <silent><Leader>q :<C-u>:quit<CR>
nnoremap <silent><Leader>a :<C-u>:quitall<CR>

" choosewin
nmap - <Plug>(choosewin)
let g:choosewin_overlay_enable=1

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
let g:ctrlp_open_new_file = 'r'
let g:ctrlp_working_path_mode = 'ra' 
let g:ctrlp_map = '<c-p>'
let g:ctrlp_cmd = 'CtrlPMRU'
nnoremap <C-o> :CtrlPBuffer<CR>

let g:ctrlp_match_func = {'match': 'cpsm#CtrlPMatch'}
"nnoremap <C-l> :CtrlPMRU<CR>

" airline stuff
"

" Select last paste
nnoremap <expr> gp '`['.strpart(getregtype(), 0, 1).'`]'

" Quick substitute within selected area
xnoremap s :s//g<Left><Left>

" command line controls
cnoremap <C-h> <Home>
cnoremap <C-l> <End>


nnoremap <silent> sh  :<C-u>split<CR>
nnoremap <silent> sv  :<C-u>vsplit<CR>
nnoremap <silent> st  :tabnew<CR>
nnoremap <silent> so  :<C-u>only<CR>
nnoremap <silent> sb  :b#<CR>
nnoremap <silent> sc  :close<CR>
nnoremap <silent> sx  :<C-u>call <SID>BufferEmpty()<CR>

" Split current buffer, go to previous window and previous buffer
nnoremap <silent><Leader>sh :split<CR>:wincmd p<CR>:e#<CR>
nnoremap <silent><Leader>sv :vsplit<CR>:wincmd p<CR>:e#<CR>

" Improve scroll, credits: https://github.com/Shougo
nnoremap <expr> zz (winline() == (winheight(0)+1) / 2) ?
	\ 'zt' : (winline() == 1) ? 'zb' : 'zz'
noremap <expr> <C-f> max([winheight(0) - 2, 1])
	\ ."\<C-d>".(line('w$') >= line('$') ? "L" : "M")
noremap <expr> <C-b> max([winheight(0) - 2, 1])
	\ ."\<C-u>".(line('w0') <= 1 ? "H" : "M")
noremap <expr> <C-e> (line("w$") >= line('$') ? "j" : "3\<C-e>")
noremap <expr> <C-y> (line("w0") <= 1         ? "k" : "3\<C-y>")

" Select last paste
nnoremap <expr> gp '`['.strpart(getregtype(), 0, 1).'`]'

" Yank buffer's absolute path to X11 clipboard
nnoremap <Leader>y :let @+=expand("%")<CR>:echo 'Relative path copied to clipboard.'<CR>
nnoremap <Leader>Y :let @+=expand("%:p")<CR>:echo 'Absolute path copied to clipboard.'<CR>

" Macros
nnoremap Q q
nnoremap gQ @q

" Toggle editor visuals
nmap <silent> <Leader>ts :setlocal spell!<cr>
nmap <silent> <Leader>tn :setlocal nonumber!<CR>
nmap <silent> <Leader>tl :setlocal nolist!<CR>
nmap <silent> <Leader>th :nohlsearch<CR>
nmap <silent> <Leader>tw :setlocal wrap! breakindent!<CR>

" Select last paste
nnoremap <expr> gp '`['.strpart(getregtype(), 0, 1).'`]'

" When pressing <leader>cd switch to the directory of the open buffer
map <Leader>cd :lcd %:p:h<CR>:pwd<CR>

" Select blocks after indenting
xnoremap < <gv
xnoremap > >gv|

" Use tab for indenting in visual mode
vnoremap <Tab> >gv|
vnoremap <S-Tab> <gv
nnoremap > >>_
nnoremap < <<_

colo onedark 
"colo happy_hacking 
"colo wal

"hi Normal guibg=NONE ctermbg=NONE

set laststatus=2
let g:airline_theme='deep_space'
"let g:airline_theme = 'wal'

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


