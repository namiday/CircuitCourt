unit UpdateUser;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls, Vcl.ExtCtrls, IdHTTP;

type
  TUpdateUserForm = class(TForm)
    lblTitreUpdateUser: TLabel;
    PanelBas: TPanel;
    btnMajUser: TButton;
    PanelDroit: TPanel;
    Panel13: TPanel;
    Label8: TLabel;
    EditContact: TEdit;
    Panel11: TPanel;
    Label9: TLabel;
    EditshopKind: TEdit;
    Panel10: TPanel;
    Label10: TLabel;
    EditPostalCode: TEdit;
    Panel9: TPanel;
    Label11: TLabel;
    EditShopStreet: TEdit;
    Panel8: TPanel;
    Label12: TLabel;
    EditShopcity: TEdit;
    Panel7: TPanel;
    Label13: TLabel;
    EditShopName: TEdit;
    Panel12: TPanel;
    Label2: TLabel;
    editshopDescription: TEdit;
    Panel15: TPanel;
    Label14: TLabel;
    EditLatitude: TEdit;
    Panel14: TPanel;
    Label15: TLabel;
    EditLongitude: TEdit;
    PanelGauche: TPanel;
    Panel5: TPanel;
    lblMail: TLabel;
    editPassword: TEdit;
    Panel4: TPanel;
    Label1: TLabel;
    EditNickName: TEdit;
    Panel35: TPanel;
    Label3: TLabel;
    EditLastName: TEdit;
    Panel2: TPanel;
    Label5: TLabel;
    editFirstName: TEdit;
    Panel1: TPanel;
    Label6: TLabel;
    EditMail: TEdit;
    Panel3: TPanel;
    lblId: TLabel;
    EditID: TEdit;
    Panel16: TPanel;
    Label4: TLabel;
    EditIDShop: TEdit;
    procedure FormCreate(Sender: TObject);
    procedure FormResize(Sender: TObject);
    procedure btnMajUserClick(Sender: TObject);
  private
    { Déclarations privées }
  public
    { Déclarations publiques }
  end;

var
  UpdateUserForm: TUpdateUserForm;

implementation

{$R *.dfm}
procedure TUpdateUserForm.btnMajUserClick(Sender: TObject);
var
 lHTTP: TIdHTTP;
 chaine,res : string;
begin
  lHTTP := TIdHTTP.Create;
  try
    chaine := 'http://ec2-34-247-50-129.eu-west-1.compute.amazonaws.com/updateUser/'+ EditID.Text + '/' + EditMail.Text +'/'+ editPassword.Text + '/' + editFirstName.Text + '/'
    + EditLastName.Text + '/' + EditNickName.Text + '/' + EditShopName.Text + '/' + EditShopcity.Text + '/' + EditShopStreet.Text + '/' + EditIDShop.Text + '/' + EditPostalCode.Text + '/'
    + EditshopKind.Text + '/' + editshopDescription.Text + '/' + EditContact.text + '/' + EditLongitude.Text + '/' + EditLatitude.Text;
    res := lHTTP.Get(chaine);
    ShowMessage(res);
  finally
    lHTTP.Free;
  end;
end;
//------------------------------------------------------------------------------

procedure TUpdateUserForm.FormCreate(Sender: TObject);
begin
PanelGauche.Width := Round((UpdateUserForm.Width/2)-10);
PanelDroit.Width := Round((UpdateUserForm.Width/2)-10);
end;

procedure TUpdateUserForm.FormResize(Sender: TObject);
begin
 PanelGauche.Width := Round((UpdateUserForm.Width/2)-10);
 PanelDroit.Width := Round((UpdateUserForm.Width/2)-10);
end;



end.
